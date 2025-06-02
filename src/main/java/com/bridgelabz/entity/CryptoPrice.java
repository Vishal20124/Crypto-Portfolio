package com.bridgelabz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class CryptoPrice {

    @Id
    private String symbol; // e.g. "btc"

    private double currentPrice;

    private LocalDateTime timestamp;

    // Constructors
    public CryptoPrice() {}

    public CryptoPrice(String symbol, double currentPrice, LocalDateTime timestamp) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.timestamp = timestamp;
    }

    // Getters & Setters
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
