package com.bridgelabz.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.entity.CryptoPrice;
import com.bridgelabz.exception.CustomExceptions.ApiException;
import com.bridgelabz.repository.CryptoPriceRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors; 

@Service
public class CryptoPriceService {

    private static final String COINGECKO_API_URL =
            "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20&page=1&x_cg_demo_api_key=CG-YHdjg5kG3VpEL1CuAXvgZFgv";

    private final RestTemplate restTemplate;
    private final CryptoPriceRepository cryptoPriceRepository;

    public CryptoPriceService(RestTemplate restTemplate, CryptoPriceRepository cryptoPriceRepository) {
        this.restTemplate = restTemplate;
        this.cryptoPriceRepository = cryptoPriceRepository;
    }

    public List<CryptoPrice> fetchAndSaveLatestPrices() {
        try {
            ResponseEntity<CoinGeckoResponse[]> response =
                    restTemplate.getForEntity(COINGECKO_API_URL, CoinGeckoResponse[].class);

            if (response.getBody() == null || response.getBody().length == 0) {
                throw new ApiException("Empty response from CoinGecko API");
            }

            List<CryptoPrice> prices = Arrays.stream(response.getBody())
                    .map(cg -> new CryptoPrice(cg.getSymbol(), cg.getCurrent_price(), LocalDateTime.now()))
                    .collect(Collectors.toList());

            cryptoPriceRepository.saveAll(prices);
            return prices;

        } catch (RestClientException e) {
            throw new ApiException("Failed to fetch prices from CoinGecko API: " + e.getMessage());
        }
    }

    // Inner class for mapping CoinGecko API response
    public static class CoinGeckoResponse {
        private String symbol;
        private double current_price;

        // getters and setters
        public String getSymbol() {
            return symbol;
        }
        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
        public double getCurrent_price() {
            return current_price;
        }
        public void setCurrent_price(double current_price) {
            this.current_price = current_price;
        }
    }
}
