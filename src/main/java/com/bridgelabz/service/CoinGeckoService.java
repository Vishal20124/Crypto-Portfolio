package com.bridgelabz.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.exception.CustomExceptions.ApiException;

@Service
public class CoinGeckoService {

    private final CoinGeckoApiClient coinGeckoApiClient;

    public CoinGeckoService(CoinGeckoApiClient coinGeckoApiClient) {
        this.coinGeckoApiClient = coinGeckoApiClient;
    }

    public Double getCurrentPrice(String symbol) {
        Double price = coinGeckoApiClient.fetchCurrentPrice(symbol);
        if (price == null) {
            throw new ApiException("Failed to fetch current price for symbol: " + symbol);
        }
        return price;
    }
}
