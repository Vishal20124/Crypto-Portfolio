package com.bridgelabz.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CoinGeckoApiClient {

    private static final String API_URL =
            "https://api.coingecko.com/api/v3/coins/markets" +
            "?vs_currency=usd&order=market_cap_desc&per_page=100&page=1" +
            "&x_cg_demo_api_key=CG-YHdjg5kG3VpEL1CuAXvgZFgv";

    public Double fetchCurrentPrice(String symbol) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(API_URL, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);

            for (JsonNode coin : root) {
                String apiSymbol = coin.get("symbol").asText();
                if (apiSymbol.equalsIgnoreCase(symbol)) {
                    return coin.get("current_price").asDouble();
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // You can replace with logger
        }

        return 0.0; // default if not found
    }
}
