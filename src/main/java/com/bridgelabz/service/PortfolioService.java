package com.bridgelabz.service;

import com.bridgelabz.dto.PortfolioAssetRequestDTO;
import com.bridgelabz.dto.PortfolioAssetResponseDTO;

import java.util.List;

public interface PortfolioService {

    PortfolioAssetResponseDTO addAsset(PortfolioAssetRequestDTO requestDTO);

    List<PortfolioAssetResponseDTO> getAllAssets();

    PortfolioAssetResponseDTO updateAsset(Long id, PortfolioAssetRequestDTO requestDTO);

    void deleteAsset(Long id);
}
