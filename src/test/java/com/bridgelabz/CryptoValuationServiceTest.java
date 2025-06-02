package com.bridgelabz;

import com.bridgelabz.dto.CryptoValuationDTO;
import com.bridgelabz.entity.CryptoHolding;
import com.bridgelabz.entity.CryptoPrice;
import com.bridgelabz.exception.CustomExceptions.ResourceNotFoundException;
import com.bridgelabz.repository.CryptoHoldingRepository;
import com.bridgelabz.repository.CryptoPriceRepository;
import com.bridgelabz.service.CryptoValuationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CryptoValuationServiceTest {

    @Mock
    private CryptoHoldingRepository holdingRepository;

    @Mock
    private CryptoPriceRepository priceRepository;

    @InjectMocks
    private CryptoValuationService cryptoValuationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getValuationsForUser_returnsValuations() {
        Long userId = 1L;

        CryptoHolding holding = new CryptoHolding();
        holding.setSymbol("BTC");
        holding.setQuantityHeld(2.0);
        holding.setBuyPrice(20000.0);

        CryptoPrice price = new CryptoPrice();
        price.setSymbol("BTC");
        price.setCurrentPrice(30000.0);

        when(holdingRepository.findByUserId(userId)).thenReturn(List.of(holding));
        when(priceRepository.findById("BTC")).thenReturn(Optional.of(price));

        List<CryptoValuationDTO> valuations = cryptoValuationService.getValuationsForUser(userId);

        assertNotNull(valuations);
        assertEquals(1, valuations.size());

        CryptoValuationDTO dto = valuations.get(0);

        assertEquals("BTC", dto.getSymbol());
        assertEquals(2.0, dto.getQuantityHeld());
        assertEquals(20000.0, dto.getBuyPrice());
        assertEquals(30000.0, dto.getCurrentPrice());

        double expectedCurrentValue = 2.0 * 30000.0;
        double expectedPnl = expectedCurrentValue - (2.0 * 20000.0);

        assertEquals(expectedCurrentValue, dto.getCurrentValue());
        assertEquals(expectedPnl, dto.getPnl());
    }

    @Test
    void getValuationsForUser_throwsResourceNotFoundException_whenPriceMissing() {
        Long userId = 1L;

        CryptoHolding holding = new CryptoHolding();
        holding.setSymbol("ETH");
        holding.setQuantityHeld(5.0);
        holding.setBuyPrice(1000.0);

        when(holdingRepository.findByUserId(userId)).thenReturn(List.of(holding));
        when(priceRepository.findById("ETH")).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,
            () -> cryptoValuationService.getValuationsForUser(userId));

        assertTrue(thrown.getMessage().contains("Price not found for symbol: ETH"));
    }
}
