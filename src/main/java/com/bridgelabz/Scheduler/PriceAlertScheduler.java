package com.bridgelabz.Scheduler;

import com.bridgelabz.entity.Alert;
import com.bridgelabz.repository.AlertRepository;
import com.bridgelabz.service.CoinGeckoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PriceAlertScheduler {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private CoinGeckoService coinGeckoService;

    @Scheduled(fixedRate = 60000) // every 60 seconds
    public void checkAlerts() {
        List<Alert> alerts = alertRepository.findByStatus("ACTIVE");

        for (Alert alert : alerts) {
            try {
                Double price = coinGeckoService.getCurrentPrice(alert.getSymbol());
                boolean shouldTrigger = false;

                if ("GREATER_THAN".equalsIgnoreCase(alert.getDirection()) && price > alert.getTriggerPrice()) {
                    shouldTrigger = true;
                } else if ("LESS_THAN".equalsIgnoreCase(alert.getDirection()) && price < alert.getTriggerPrice()) {
                    shouldTrigger = true;
                }

                if (shouldTrigger) {
                    alert.setStatus("TRIGGERED");
                    alert.setTriggeredAt(LocalDateTime.now());
                    alertRepository.save(alert);
                    System.out.println("🔔 Alert triggered for " + alert.getSymbol() + " at $" + price);
                }

            } catch (Exception e) {
                System.err.println("Error checking alert: " + e.getMessage());
            }
        }
    }
}
