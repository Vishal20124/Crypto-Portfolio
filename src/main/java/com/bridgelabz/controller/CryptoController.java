package com.bridgelabz.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.dto.CryptoValuationDTO;
import com.bridgelabz.entity.CryptoPrice;
import com.bridgelabz.service.CryptoPriceService;
import com.bridgelabz.service.CryptoValuationService;

import java.util.List;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final CryptoPriceService priceService;
    private final CryptoValuationService valuationService;

    public CryptoController(CryptoPriceService priceService, CryptoValuationService valuationService) {
        this.priceService = priceService;
        this.valuationService = valuationService;
    }

    // Fetch latest prices from CoinGecko and save
    @GetMapping("/refresh-prices")
    public ResponseEntity<List<CryptoPrice>> refreshPrices() {
        List<CryptoPrice> prices = priceService.fetchAndSaveLatestPrices();
        return ResponseEntity.ok(prices);
    }

    @GetMapping("/valuation/{userId}")
    public ResponseEntity<List<CryptoValuationDTO>> getUserValuation(@PathVariable Long userId) {
        List<CryptoValuationDTO> valuations = valuationService.getValuationsForUser(userId);
        return ResponseEntity.ok(valuations);
    }
}