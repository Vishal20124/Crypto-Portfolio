package com.bridgelabz.Controller;

import com.bridgelabz.controller.MarketController;
import com.bridgelabz.dto.MarketDataDTO;
import com.bridgelabz.service.CryptoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MarketControllerTest {

    private CryptoService cryptoService;
    private MarketController marketController;

    @BeforeEach
    void setUp() {
        cryptoService = mock(CryptoService.class);
        marketController = new MarketController(cryptoService);
    }

    @Test
    void testGetTopCoinsMarketData() {
        MarketDataDTO dto = new MarketDataDTO();
        dto.setId("bitcoin");
        dto.setSymbol("BTC");
        dto.setName("Bitcoin");
        dto.setCurrent_price(30000.0);
        dto.setMarket_cap(600000000000.0);
        dto.setPrice_change_percentage_24h(2.5);

        when(cryptoService.getTopMarketData()).thenReturn(List.of(dto));

        var response = marketController.getTopCoinsMarketData();

        assertNotNull(response);
        assertEquals(1, response.getBody().size());
        MarketDataDTO result = response.getBody().get(0);

        assertEquals("BTC", result.getSymbol());
        assertEquals(30000.0, result.getCurrent_price());
        assertEquals(600000000000.0, result.getMarket_cap());
        assertEquals(2.5, result.getPrice_change_percentage_24h());
    }
}
