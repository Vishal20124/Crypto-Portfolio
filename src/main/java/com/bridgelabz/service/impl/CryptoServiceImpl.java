package com.bridgelabz.service.impl;

import com.bridgelabz.dto.MarketDataDTO;
import com.bridgelabz.service.CryptoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CryptoServiceImpl implements CryptoService {

    private static final String COINGECKO_API = "https://api.coingecko.com/api/v3/coins/markets" +
            "?vs_currency=usd&order=market_cap_desc&per_page=20&page=1&x_cg_demo_api_key=CG-YHdjg5kG3VpEL1CuAXvgZFgv";

    @Override
    public List<MarketDataDTO> getTopMarketData() {
        RestTemplate restTemplate = new RestTemplate();
        MarketDataDTO[] data = restTemplate.getForObject(COINGECKO_API, MarketDataDTO[].class);
        return Arrays.asList(data);
    }
}
