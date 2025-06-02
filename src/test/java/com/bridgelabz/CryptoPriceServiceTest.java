package com.bridgelabz;

import com.bridgelabz.entity.CryptoPrice;
import com.bridgelabz.exception.CustomExceptions.ApiException;
import com.bridgelabz.repository.CryptoPriceRepository;
import com.bridgelabz.service.CryptoPriceService;
import com.bridgelabz.service.CryptoPriceService.CoinGeckoResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CryptoPriceServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CryptoPriceRepository cryptoPriceRepository;

    private CryptoPriceService cryptoPriceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cryptoPriceService = new CryptoPriceService(restTemplate, cryptoPriceRepository);
    }

    @Test
    void testFetchAndSaveLatestPrices_success() {
        // Arrange
        CoinGeckoResponse mockResponse = new CoinGeckoResponse();
        mockResponse.setSymbol("btc");
        mockResponse.setCurrent_price(50000.0);

        CoinGeckoResponse[] responseArray = {mockResponse};
        ResponseEntity<CoinGeckoResponse[]> responseEntity = new ResponseEntity<>(responseArray, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(CoinGeckoResponse[].class))).thenReturn(responseEntity);

        // Act
        List<CryptoPrice> result = cryptoPriceService.fetchAndSaveLatestPrices();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("btc", result.get(0).getSymbol());
        assertEquals(50000.0, result.get(0).getCurrentPrice());

        // Verify saveAll is called
        verify(cryptoPriceRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testFetchAndSaveLatestPrices_emptyApiResponse_throwsException() {
        // Arrange
        ResponseEntity<CoinGeckoResponse[]> emptyResponse = new ResponseEntity<>(new CoinGeckoResponse[]{}, HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(CoinGeckoResponse[].class))).thenReturn(emptyResponse);

        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> {
            cryptoPriceService.fetchAndSaveLatestPrices();
        });

        assertEquals("Empty response from CoinGecko API", exception.getMessage());
    }

    @Test
    void testFetchAndSaveLatestPrices_apiThrowsException() {
        // Arrange
        when(restTemplate.getForEntity(anyString(), eq(CoinGeckoResponse[].class)))
                .thenThrow(new RestClientException("Connection failed"));

        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> {
            cryptoPriceService.fetchAndSaveLatestPrices();
        });

        assertTrue(exception.getMessage().contains("Failed to fetch prices from CoinGecko API"));
    }
}
