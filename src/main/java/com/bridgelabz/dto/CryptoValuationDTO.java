package com.bridgelabz.dto;

public class CryptoValuationDTO {

    private String symbol;
    private double quantityHeld;
    private double buyPrice;
    private double currentPrice;
    private double currentValue; // quantityHeld * currentPrice
    private double pnl;          // currentValue - (buyPrice * quantityHeld)

    public CryptoValuationDTO() {}

    public CryptoValuationDTO(String symbol, double quantityHeld, double buyPrice, double currentPrice, double currentValue, double pnl) {
        this.symbol = symbol;
        this.quantityHeld = quantityHeld;
        this.buyPrice = buyPrice;
        this.currentPrice = currentPrice;
        this.currentValue = currentValue;
        this.pnl = pnl;
    }

    // Getters & setters

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

    public double getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getCurrentValue() {
        return currentValue;
    }
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getPnl() {
        return pnl;
    }
    public void setPnl(double pnl) {
        this.pnl = pnl;
    }
}

