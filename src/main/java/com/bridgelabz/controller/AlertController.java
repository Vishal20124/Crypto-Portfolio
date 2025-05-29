package com.bridgelabz.controller;

import com.bridgelabz.dto.AlertRequest;
import com.bridgelabz.entity.Alert;
import com.bridgelabz.service.AlertService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    // ✅ Create a new alert with validation
    @PostMapping("/create")
    public Alert createAlert(@Valid @RequestBody AlertRequest alertRequest) {
        return alertService.createAlert(alertRequest);
    }

    // ✅ Get alerts by userId (passed via request param)
    @GetMapping("/my")
    public List<Alert> getMyAlerts(@RequestParam Long userId) {
        return alertService.getAlertsByUserId(userId);
    }

    // ✅ Get all triggered alerts
    @GetMapping("/triggered")
    public List<Alert> getTriggeredAlerts() {
        return alertService.getTriggeredAlerts();
    }
}
