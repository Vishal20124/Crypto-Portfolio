package com.bridgelabz.service;

import com.bridgelabz.dto.CryptoValuationDTO;
import com.bridgelabz.entity.CryptoHolding;
import com.bridgelabz.entity.CryptoPrice;
import com.bridgelabz.exception.CustomExceptions.ResourceNotFoundException;
import com.bridgelabz.repository.CryptoHoldingRepository;
import com.bridgelabz.repository.CryptoPriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptoValuationService {

    private final CryptoHoldingRepository holdingRepository;
    private final CryptoPriceRepository priceRepository;

    public CryptoValuationService(CryptoHoldingRepository holdingRepository, CryptoPriceRepository priceRepository) {
        this.holdingRepository = holdingRepository;
        this.priceRepository = priceRepository;
    }

    public List<CryptoValuationDTO> getValuationsForUser(Long userId) {
        List<CryptoHolding> holdings = holdingRepository.findByUserId(userId);

        return holdings.stream().map(holding -> {
            CryptoPrice price = priceRepository.findById(holding.getSymbol())
                .orElseThrow(() -> new ResourceNotFoundException("Price not found for symbol: " + holding.getSymbol()));

            double currentValue = holding.getQuantityHeld() * price.getCurrentPrice();
            double pnl = currentValue - (holding.getBuyPrice() * holding.getQuantityHeld());

            return new CryptoValuationDTO(
                    holding.getSymbol(),
                    holding.getQuantityHeld(),
                    holding.getBuyPrice(),
                    price.getCurrentPrice(),
                    currentValue,
                    pnl
            );
        }).collect(Collectors.toList());
    }
}
