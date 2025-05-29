package com.bridgelabz.controller;

import com.bridgelabz.dto.MarketDataDTO;
import com.bridgelabz.service.CryptoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(marketData);
    }
}
