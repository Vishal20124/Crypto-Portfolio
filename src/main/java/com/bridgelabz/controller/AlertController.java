package com.bridgelabz.controller;

import com.bridgelabz.entity.Alert;
import com.bridgelabz.exception.ResourceNotFoundException;
import com.bridgelabz.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping("/create")
    public Alert createAlert(@RequestBody Alert alert) {
        return alertService.createAlert(alert);
    }
  
    @GetMapping("/test-error")
    public String testException() {
        throw new ResourceNotFoundException("This is a test exception");
    }

    @GetMapping("/my")
    public List<Alert> getMyAlerts(@RequestParam Long userId) {
        return alertService.getAlertsByUserId(userId);
    }

    @GetMapping("/triggered")
    public List<Alert> getTriggeredAlerts() {
        return alertService.getTriggeredAlerts();
    }
}

