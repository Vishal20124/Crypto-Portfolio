package com.bridgelabz.service.impl;

import com.bridgelabz.dto.PortfolioAssetRequestDTO;
import com.bridgelabz.dto.PortfolioAssetResponseDTO;
import com.bridgelabz.entity.PortfolioAsset;
import com.bridgelabz.exception.CustomExceptions.ResourceNotFoundException;
import com.bridgelabz.repository.PortfolioAssetRepository;
import com.bridgelabz.service.CoinGeckoService;
import com.bridgelabz.service.PortfolioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioAssetRepository repository;

    @Autowired
    private CoinGeckoService coinGeckoService;

    @Override
    public PortfolioAssetResponseDTO addAsset(PortfolioAssetRequestDTO requestDTO) {
        PortfolioAsset asset = new PortfolioAsset();
        asset.setCoinName(requestDTO.getCoinName());
        asset.setSymbol(requestDTO.getSymbol().toUpperCase());
        asset.setQuantityHeld(requestDTO.getQuantityHeld());
        asset.setBuyPrice(requestDTO.getBuyPrice());
        asset.setBuyDate(requestDTO.getBuyDate());

        PortfolioAsset saved = repository.save(asset);
        return mapToResponse(saved);
    }

    @Override
    public PortfolioAssetResponseDTO updateAsset(Long id, PortfolioAssetRequestDTO requestDTO) {
        PortfolioAsset asset = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + id));

        asset.setCoinName(requestDTO.getCoinName());
        asset.setSymbol(requestDTO.getSymbol().toUpperCase());
        asset.setQuantityHeld(requestDTO.getQuantityHeld());
        asset.setBuyPrice(requestDTO.getBuyPrice());
        asset.setBuyDate(requestDTO.getBuyDate());

        PortfolioAsset updated = repository.save(asset);
        return mapToResponse(updated);
    }

    @Override
    public void deleteAsset(Long id) {
        repository.deleteById(id);
    }

    private PortfolioAssetResponseDTO mapToResponse(PortfolioAsset asset) {
        PortfolioAssetResponseDTO dto = new PortfolioAssetResponseDTO();
        dto.setId(asset.getId());
        dto.setCoinName(asset.getCoinName());
        dto.setSymbol(asset.getSymbol());
        dto.setQuantityHeld(asset.getQuantityHeld());
        dto.setBuyPrice(asset.getBuyPrice());
        dto.setBuyDate(asset.getBuyDate());
        return dto;
    }

    @Override
    public List<PortfolioAssetResponseDTO> getAllAssets() {
        List<PortfolioAsset> assets = repository.findAll();

        return assets.stream().map(asset -> {
            PortfolioAssetResponseDTO dto = mapToResponse(asset);

            Double currentPrice = coinGeckoService.getCurrentPrice(asset.getSymbol());
            if (currentPrice == null || currentPrice == 0.0) {
                currentPrice = asset.getBuyPrice();
            }
            dto.setCurrentPrice(currentPrice);
            dto.setTotalValue(currentPrice * asset.getQuantityHeld());
            dto.setProfitOrLoss((currentPrice - asset.getBuyPrice()) * asset.getQuantityHeld());

            return dto;
        }).collect(Collectors.toList());
    }
}
