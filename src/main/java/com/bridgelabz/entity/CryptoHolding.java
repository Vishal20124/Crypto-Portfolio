package com.bridgelabz.entity;

import jakarta.persistence.*;

@Entity
public class CryptoHolding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // For demo purposes; associate holding to user

    private String symbol; // e.g., "btc"

    private double quantityHeld;

    private double buyPrice;

    // Constructors
    public CryptoHolding() {}

    public CryptoHolding(Long userId, String symbol, double quantityHeld, double buyPrice) {
        this.userId = userId;
        this.symbol = symbol;
        this.quantityHeld = quantityHeld;
        this.buyPrice = buyPrice;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getQuantityHeld() {
        return quantityHeld;
    }
    public void setQuantityHeld(double quantityHeld) {
        this.quantityHeld = quantityHeld;
    }

    public double getBuyPrice() {
        return buyPrice;
    }
    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }
}
