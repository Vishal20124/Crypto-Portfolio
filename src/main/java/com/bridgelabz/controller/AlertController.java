package com.bridgelabz.controller;

import com.bridgelabz.entity.Alert;
import com.bridgelabz.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bridgelabz.exception.CustomExceptions.*;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping("/create")
    public Alert createAlert(@RequestBody Alert alert) {
        return alertService.createAlert(alert);
    }

    @GetMapping("/test-price")
    public Double testGetCurrentPrice(@RequestParam String symbol) {
        Double price = alertService.getCurrentPriceForTest(symbol);
        if (price == null) {
            throw new ResourceNotFoundException("Price not found for symbol: " + symbol);
        }
        return price;
    }

    @GetMapping("/my")
    public List<Alert> getMyAlerts(@RequestParam Long userId) {
        List<Alert> alerts = alertService.getAlertsByUserId(userId);
        if (alerts.isEmpty()) {
            throw new ResourceNotFoundException("No alerts found for user with ID: " + userId);
        }
        return alerts;
    }

    @GetMapping("/triggered")
    public List<Alert> getTriggeredAlerts() {
        List<Alert> alerts = alertService.getTriggeredAlerts();
        if (alerts.isEmpty()) {
            throw new ResourceNotFoundException("No triggered alerts found");
        }
        return alerts;
    }
}
