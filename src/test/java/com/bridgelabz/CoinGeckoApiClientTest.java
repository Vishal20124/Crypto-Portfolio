package com.bridgelabz;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.service.CoinGeckoApiClient;

public class CoinGeckoApiClientTest {

    private RestTemplate restTemplate;
    private CoinGeckoApiClient coinGeckoApiClient;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);  // Mock the dependency
        coinGeckoApiClient = new CoinGeckoApiClient(restTemplate); // Inject mock
    }

    @Test
    void testFetchCurrentPrice_validSymbol_returnsPrice() {
        // Mocked JSON response from CoinGecko API
        String mockResponse = "[{\"symbol\":\"btc\",\"current_price\":30000.0}]";

        // Define the behavior of restTemplate
        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenReturn(mockResponse);

        // Test the method
        Double price = coinGeckoApiClient.fetchCurrentPrice("btc");

        // Assert
        assertNotNull(price);
        assertEquals(30000.0, price);
    }

    @Test
    void testFetchCurrentPrice_symbolNotFound_returnsNull() {
        String mockResponse = "[{\"symbol\":\"eth\",\"current_price\":2000.0}]";

        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenReturn(mockResponse);

        Double price = coinGeckoApiClient.fetchCurrentPrice("doge");

        assertNull(price);
    }

    @Test
    void testFetchCurrentPrice_apiThrowsException_returnsNull() {
        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenThrow(new RuntimeException("API failed"));

        Double price = coinGeckoApiClient.fetchCurrentPrice("btc");

        assertNull(price);
    }
}
