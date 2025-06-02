package com.bridgelabz.Controller;

import com.bridgelabz.controller.PortfolioController;
import com.bridgelabz.dto.PortfolioAssetRequestDTO;
import com.bridgelabz.dto.PortfolioAssetResponseDTO;
import com.bridgelabz.service.PortfolioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PortfolioControllerTest {

    private PortfolioService portfolioService;
    private PortfolioController portfolioController;

    @BeforeEach
    void setUp() {
        portfolioService = mock(PortfolioService.class);
        portfolioController = new PortfolioController();
        portfolioController = new PortfolioController();
        // Using reflection to inject mock since field is autowired
        try {
            var field = PortfolioController.class.getDeclaredField("portfolioService");
            field.setAccessible(true);
            field.set(portfolioController, portfolioService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    
    }
    @Test
    void testAddAsset_Success() {
        PortfolioAssetRequestDTO request = new PortfolioAssetRequestDTO();
        request.setCoinName("Bitcoin");
        request.setSymbol("BTC");
        request.setQuantityHeld(2.0);
        request.setBuyPrice(25000.0);
        request.setBuyDate(LocalDate.now());

        PortfolioAssetResponseDTO response = new PortfolioAssetResponseDTO();
        response.setId(1L);
        response.setCoinName("Bitcoin");

        when(portfolioService.addAsset(request)).thenReturn(response);

        var result = portfolioController.addAsset(request);

        assertNotNull(result.getBody());
        assertEquals("Bitcoin", result.getBody().getCoinName());
    }

    @Test
    void testGetAllAssets_Success() {
        PortfolioAssetResponseDTO dto = new PortfolioAssetResponseDTO();
        dto.setId(1L);
        dto.setCoinName("ETH");

        when(portfolioService.getAllAssets()).thenReturn(List.of(dto));

        var result = portfolioController.getAllAssets();

        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
        assertEquals("ETH", result.getBody().get(0).getCoinName());
    }

    @Test
    void testUpdateAsset_Success() {
        Long assetId = 1L;

        PortfolioAssetRequestDTO request = new PortfolioAssetRequestDTO();
        request.setCoinName("UpdatedCoin");

        PortfolioAssetResponseDTO response = new PortfolioAssetResponseDTO();
        response.setId(assetId);
        response.setCoinName("UpdatedCoin");
    }
    }
