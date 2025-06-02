package com.bridgelabz;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.bridgelabz.entity.Alert;
import com.bridgelabz.service.AlertService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AlertServiceTest {

    @Mock
    private AlertService alertService;

    public AlertServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAlert() {
        Alert alert = new Alert();
        
        alert.setId(34L);
        alert.setSymbol("BTC");
        alert.setTriggerPrice(55000.0);
        alert.setDirection("above");
        alert.setUserId(1L);
        alert.setStatus("pending");
        alert.setTriggered(true);
//        alert.setTriggeredAt();
       

        // Mock the service method correctly
        when(alertService.createAlert(alert)).thenReturn(alert);

        // Call the method
        Alert createdAlert = alertService.createAlert(alert);

        // Assert returned object values
        assertNotNull(createdAlert);
        assertEquals("BTC", createdAlert.getSymbol());
        assertEquals(55000.0, createdAlert.getTriggerPrice());
        assertEquals("above", createdAlert.getDirection());
        assertFalse(createdAlert.isTriggered());
    }
}
