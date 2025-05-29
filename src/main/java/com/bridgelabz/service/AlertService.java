package com.bridgelabz.service;

import com.bridgelabz.entity.Alert;
import com.bridgelabz.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public Alert createAlert(Alert alert) {
        alert.setStatus("active");
        alert.setTriggeredAt(null);
        return alertRepository.save(alert);
    }

    public List<Alert> getAlertsByUserId(long i) {
        return alertRepository.findByUserId(i);
    }

    public List<Alert> getTriggeredAlerts() {
        return alertRepository.findByStatus("triggered");
    }

    public void markAsTriggered(Long alertId) {
        alertRepository.findById(alertId).ifPresent(alert -> {
            alert.setStatus("triggered");
            alert.setTriggeredAt(LocalDateTime.now());
            alertRepository.save(alert);
        });
    }
}

