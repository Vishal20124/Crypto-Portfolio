package com.bridgelabz;

import com.bridgelabz.exception.CustomExceptions.ApiException;
import com.bridgelabz.service.CoinGeckoApiClient;
import com.bridgelabz.service.CoinGeckoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CoinGeckoServiceTest {

    private CoinGeckoApiClient mockApiClient;
    private CoinGeckoService coinGeckoService;

    @BeforeEach
    void setUp() {
        mockApiClient = mock(CoinGeckoApiClient.class);
        coinGeckoService = new CoinGeckoService(mockApiClient);
    }

    @Test
    void testGetCurrentPrice_successfulFetch() {
        // Arrange
        when(mockApiClient.fetchCurrentPrice("btc")).thenReturn(30000.0);

        // Act
        Double price = coinGeckoService.getCurrentPrice("btc");

        // Assert
        assertNotNull(price);
        assertEquals(30000.0, price);
    }

    @Test
    void testGetCurrentPrice_nullFromApi_throwsException() {
        // Arrange
        when(mockApiClient.fetchCurrentPrice("doge")).thenReturn(null);

        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> {
            coinGeckoService.getCurrentPrice("doge");
        });

        assertEquals("Failed to fetch current price for symbol: doge", exception.getMessage());
    }
}
