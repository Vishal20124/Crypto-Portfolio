package com.bridgelabz.controller;

import com.bridgelabz.dto.PortfolioAssetRequestDTO;
import com.bridgelabz.dto.PortfolioAssetResponseDTO;
import com.bridgelabz.service.PortfolioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bridgelabz.exception.CustomExceptions.*;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/add")
    public ResponseEntity<PortfolioAssetResponseDTO> addAsset(@RequestBody PortfolioAssetRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new BadRequestException("Portfolio asset request cannot be null.");
        }
        PortfolioAssetResponseDTO responseDTO = portfolioService.addAsset(requestDTO);
        if (responseDTO == null) {
            throw new ApiException("Failed to add portfolio asset.");
        }
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/my")
    public ResponseEntity<List<PortfolioAssetResponseDTO>> getAllAssets() {
        List<PortfolioAssetResponseDTO> assets = portfolioService.getAllAssets();
        if (assets == null || assets.isEmpty()) {
            throw new ResourceNotFoundException("No portfolio assets found.");
        }
        return ResponseEntity.ok(assets);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PortfolioAssetResponseDTO> updateAsset(@PathVariable Long id,
                                                                @RequestBody PortfolioAssetRequestDTO requestDTO) {
        if (id == null || id <= 0) {
            throw new BadRequestException("Invalid asset ID.");
        }
        if (requestDTO == null) {
            throw new BadRequestException("Update request data cannot be null.");
        }
        PortfolioAssetResponseDTO responseDTO = portfolioService.updateAsset(id, requestDTO);
        if (responseDTO == null) {
            throw new ResourceNotFoundException("Portfolio asset with ID " + id + " not found.");
        }
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new BadRequestException("Invalid asset ID.");
        }
        portfolioService.deleteAsset(id);  // void method; throws if not found
        return ResponseEntity.ok("Asset deleted successfully.");
    }
}
