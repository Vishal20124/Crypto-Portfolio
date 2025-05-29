package com.bridgelabz;

import com.bridgelabz.entity.Alert;
import com.bridgelabz.repository.AlertRepository;
import com.bridgelabz.service.AlertService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AlertServiceTest {

    @Mock
    private AlertRepository alertRepository;

    @InjectMocks
    private AlertService alertService;

    @Test
    public void testCreateAlert() {
        Alert alert = new Alert();
        alert.setUserId(2);
        alert.setSymbol("SOL");
        alert.setTriggerPrice(90.0);
        alert.setDirection("BELOW");

        when(alertRepository.save(any(Alert.class))).thenReturn(alert);

        Alert created = alertService.createAlert(alert);
        assertEquals("SOL", created.getSymbol());
        verify(alertRepository, times(1)).save(alert);
    }

    @Test
    public void testGetAlertsByUserId() {
        Alert alert = new Alert();
        alert.setUserId(2);

        when(alertRepository.findByUserId(2)).thenReturn(List.of(alert));

        List<Alert> alerts = alertService.getAlertsByUserId(2);
        assertEquals(1, alerts.size());
    }

    @Test
    public void testGetTriggeredAlerts() {
        Alert alert = new Alert();
        alert.setStatus("triggered");

        when(alertRepository.findByStatus("triggered")).thenReturn(List.of(alert));

        List<Alert> triggered = alertService.getTriggeredAlerts();
        assertEquals("triggered", triggered.get(0).getStatus());
    }
}
