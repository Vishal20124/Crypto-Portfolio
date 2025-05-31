package com.bridgelabz.controller;

import com.bridgelabz.dto.MarketDataDTO;
import com.bridgelabz.service.CryptoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bridgelabz.exception.CustomExceptions.*;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    private final CryptoService cryptoService;

    public MarketController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("/top-coins")
    public ResponseEntity<List<MarketDataDTO>> getTopCoinsMarketData() {
        List<MarketDataDTO> marketData = cryptoService.getTopMarketData();
        if (marketData == null || marketData.isEmpty()) {
            throw new ApiException("Failed to fetch top market data or no data available.");
        }
        return ResponseEntity.ok(marketData);
    }
}
