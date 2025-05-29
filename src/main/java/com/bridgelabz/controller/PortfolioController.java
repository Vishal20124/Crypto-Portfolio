package com.bridgelabz.PortfolioAPIs.Controller;

import com.bridgelabz.PortfolioAPIs.entity.CryptoHolding;
import com.bridgelabz.PortfolioAPIs.entity.CryptoPrice;
import com.bridgelabz.PortfolioAPIs.Repository.CryptoHoldingRepository;
import com.bridgelabz.PortfolioAPIs.Repository.CryptoPriceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    @Autowired
    private CryptoHoldingRepository cryptoHoldingRepository;

    @Autowired
    private CryptoPriceRepository cryptoPriceRepository;

    // --------- Crypto Holdings Endpoints ---------

    @GetMapping("/portfolio/my")
    public List<CryptoHolding> getAllHoldings() {
        return cryptoHoldingRepository.findAll();
    }

    @GetMapping("/holdings/{id}")
    public Optional<CryptoHolding> getHoldingById(@PathVariable int id) {
        return cryptoHoldingRepository.findById(id);
    }

    @PostMapping("/portfolio/add")
    public CryptoHolding addHolding(@RequestBody CryptoHolding holding) {
        return cryptoHoldingRepository.save(holding);
    }

    @PutMapping("portfolio/update/{id}")
    public CryptoHolding updateHolding(@PathVariable int id, @RequestBody CryptoHolding updatedHolding) {
        return cryptoHoldingRepository.findById(id).map(existing -> {
            existing.setUserId(updatedHolding.getUserId());
            existing.setCoinName(updatedHolding.getCoinName());
            existing.setSymbol(updatedHolding.getSymbol());
            existing.setQuantityHeld(updatedHolding.getQuantityHeld());
            existing.setBuyPrice(updatedHolding.getBuyPrice());
            existing.setBuyDate(updatedHolding.getBuyDate());
            return cryptoHoldingRepository.save(existing);
        }).orElseGet(() -> {
            updatedHolding.setId(id);
            return cryptoHoldingRepository.save(updatedHolding);
        });
    }

    @DeleteMapping("portfolio/delete/{id}")
    public String deleteHolding(@PathVariable int id) {
        cryptoHoldingRepository.deleteById(id);
        return "Holding with ID " + id + " deleted.";
    }

    // --------- Crypto Prices Endpoints ---------

    @GetMapping("/prices")
    public List<CryptoPrice> getAllPrices() {
        return cryptoPriceRepository.findAll();
    }

    @GetMapping("/prices/{symbol}")
    public Optional<CryptoPrice> getPriceBySymbol(@PathVariable int symbol) {
        return cryptoPriceRepository.findById(symbol);
    }

    @PostMapping("/prices")
    public CryptoPrice addPrice(@RequestBody CryptoPrice price) {
        return cryptoPriceRepository.save(price);
    }

    @PutMapping("/prices/{symbol}") 
    public CryptoPrice updatePrice(@PathVariable int symbol, @RequestBody CryptoPrice updatedPrice) {
        return cryptoPriceRepository.findById(symbol).map(existing -> {
            existing.setCurrentPrice(updatedPrice.getCurrentPrice());
            existing.setTimestamp(updatedPrice.getTimestamp());
            return cryptoPriceRepository.save(existing);
        }).orElseGet(() -> {
            updatedPrice.setSymbol(symbol);
            return cryptoPriceRepository.save(updatedPrice);
        });
    }

    @DeleteMapping("/prices/{symbol}")
    public String deletePrice(@PathVariable int symbol) {
        cryptoPriceRepository.deleteById(symbol);
        return "Price with symbol " + symbol + " deleted.";
    }
}
