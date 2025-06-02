package com.bridgelabz.Controller;

import com.bridgelabz.controller.CryptoController;
import com.bridgelabz.dto.CryptoValuationDTO;
import com.bridgelabz.service.CryptoValuationService;
import com.bridgelabz.service.CryptoPriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CryptoControllerTest {

    @Mock
    private CryptoPriceService priceService;

    @Mock
    private CryptoValuationService valuationService;

    private CryptoController cryptoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cryptoController = new CryptoController(priceService, valuationService);
    }

    @Test
    void testGetUserValuation() {
        // Arrange: prepare a sample CryptoValuationDTO object
        CryptoValuationDTO dto = new CryptoValuationDTO();
        dto.setSymbol("ETH");
        dto.setQuantityHeld(2.0);
        dto.setBuyPrice(1500.0);
        dto.setCurrentPrice(1800.0);
        dto.setCurrentValue(3600.0);
        dto.setPnl(600.0);

        when(valuationService.getValuationsForUser(1L)).thenReturn(List.of(dto));

        // Act: call the controller method
        ResponseEntity<List<CryptoValuationDTO>> response = cryptoController.getUserValuation(1L);

        // Assert: verify the response
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        List<CryptoValuationDTO> valuations = response.getBody();
        assertNotNull(valuations);
        assertEquals(1, valuations.size());

        CryptoValuationDTO resultDto = valuations.get(0);
        assertEquals("ETH", resultDto.getSymbol());
        assertEquals(2.0, resultDto.getQuantityHeld());
        assertEquals(1500.0, resultDto.getBuyPrice());
        assertEquals(1800.0, resultDto.getCurrentPrice());
        assertEquals(3600.0, resultDto.getCurrentValue());
        assertEquals(600.0, resultDto.getPnl());
    }
}
