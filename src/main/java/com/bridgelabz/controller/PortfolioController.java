package com.bridgelabz.controller;

import com.bridgelabz.dto.PortfolioAssetRequestDTO;
import com.bridgelabz.dto.PortfolioAssetResponseDTO;
import com.bridgelabz.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/add")
    public ResponseEntity<PortfolioAssetResponseDTO> addAsset(@RequestBody PortfolioAssetRequestDTO requestDTO) {
        PortfolioAssetResponseDTO responseDTO = portfolioService.addAsset(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/my")
    public ResponseEntity<List<PortfolioAssetResponseDTO>> getAllAssets() {
        List<PortfolioAssetResponseDTO> assets = portfolioService.getAllAssets();
        return ResponseEntity.ok(assets);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PortfolioAssetResponseDTO> updateAsset(@PathVariable Long id,
                                                                @RequestBody PortfolioAssetRequestDTO requestDTO) {
        PortfolioAssetResponseDTO responseDTO = portfolioService.updateAsset(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable Long id) {
        portfolioService.deleteAsset(id);
        return ResponseEntity.ok("Asset deleted successfully.");
    }
}
