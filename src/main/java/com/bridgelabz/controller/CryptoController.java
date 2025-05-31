package com.bridgelabz.controller;

import com.bridgelabz.dto.CryptoValuationDTO;
import com.bridgelabz.entity.CryptoPrice;
import com.bridgelabz.service.CryptoPriceService;
import com.bridgelabz.service.CryptoValuationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bridgelabz.exception.CustomExceptions.*;

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
        if (prices == null || prices.isEmpty()) {
            throw new ApiException("Failed to fetch or save latest crypto prices.");
        }
        return ResponseEntity.ok(prices);
    }

    @GetMapping("/valuation/{userId}")
    public ResponseEntity<List<CryptoValuationDTO>> getUserValuation(@PathVariable Long userId) {
        if (userId == null || userId <= 0) {
            throw new BadRequestException("Invalid userId provided.");
        }
        List<CryptoValuationDTO> valuations = valuationService.getValuationsForUser(userId);
        if (valuations == null || valuations.isEmpty()) {
            throw new ResourceNotFoundException("No valuations found for user with ID: " + userId);
        }
        return ResponseEntity.ok(valuations);
    }
}
