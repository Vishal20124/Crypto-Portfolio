package com.bridgelabz.Controller;

import com.bridgelabz.controller.AlertController;
import com.bridgelabz.entity.Alert;
import com.bridgelabz.service.AlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AlertControllerTest {

    private AlertService alertService;
    private AlertController alertController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        alertService = mock(AlertService.class);
        alertController = new AlertController(alertService);
    }

    @Test
    void testCreateAlert() {
        Alert alert = new Alert();
        alert.setId(1L);
        alert.setSymbol("BTC");
        alert.setTriggerPrice(55000.0);
        alert.setDirection("above");
        alert.setUserId(1L);
        alert.setStatus("pending");
        alert.setTriggered(false);

        when(alertService.createAlert(alert)).thenReturn(alert);

        Alert result = alertController.createAlert(alert);

        assertNotNull(result);
        assertEquals("BTC", result.getSymbol());
        assertEquals(55000.0, result.getTriggerPrice());
        assertEquals("above", result.getDirection());
        assertFalse(result.isTriggered());
    }
}
