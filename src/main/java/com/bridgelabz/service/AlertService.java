package com.bridgelabz.service;

import com.bridgelabz.entity.Alert;
import com.bridgelabz.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private CoinGeckoService coinGeckoService;
 
    public Alert createAlert(Alert alert) {
        alert.setStatus("pending");
        return alertRepository.save(alert);
    }

    public List<Alert> getAlertsByUserId(long i) {
        return alertRepository.findByUserId(i);
    }

    public List<Alert> getTriggeredAlerts() {
        return alertRepository.findByStatus("triggered");
    }
    
    public Double getCurrentPriceForTest(String symbol) {
        String url = UriComponentsBuilder.fromUriString("https://api.coingecko.com/api/v3/simple/price")
                .queryParam("ids", symbol.toLowerCase())
                .queryParam("vs_currencies", "usd")
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        Map<String, Map<String, Object>> body = response.getBody();

        if (body != null && body.containsKey(symbol.toLowerCase())) {
            Object priceObj = body.get(symbol.toLowerCase()).get("usd");
            if (priceObj instanceof Number) {
                return ((Number) priceObj).doubleValue();
            }
        }
        return null;
    }
    
    public void checkAndTriggerAlerts() {
        List<Alert> alerts = alertRepository.findByStatus("pending");

        for (Alert alert : alerts) {
            Double currentPrice = coinGeckoService.getCurrentPrice(alert.getSymbol());

            if (currentPrice == null) {
                continue; // skip if price not found
            }

            boolean shouldTrigger = 
                ("ABOVE".equalsIgnoreCase(alert.getDirection()) && currentPrice >= alert.getTriggerPrice()) ||
                ("BELOW".equalsIgnoreCase(alert.getDirection()) && currentPrice <= alert.getTriggerPrice());

            if (shouldTrigger) {
                alert.setStatus("triggered");
                alert.setTriggeredAt(LocalDateTime.now());
                alertRepository.save(alert);
            }
        }
    }
}