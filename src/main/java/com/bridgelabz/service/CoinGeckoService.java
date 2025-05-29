package com.bridgelabz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoinGeckoService {

    @Autowired
    private CoinGeckoApiClient coinGeckoApiClient;

    public Double getCurrentPrice(String symbol) {
        return coinGeckoApiClient.fetchCurrentPrice(symbol);
    }
}
