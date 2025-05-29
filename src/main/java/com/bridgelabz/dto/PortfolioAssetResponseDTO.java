package com.bridgelabz.dto;


import java.time.LocalDate;

public class PortfolioAssetResponseDTO {

    private Long id;
    private String coinName;
    private String symbol;
    private double quantityHeld;
    private double buyPrice;
    private LocalDate buyDate;

    private Double currentPrice;   // from CoinGecko
    private Double totalValue;     // currentPrice * quantityHeld
    private Double profitOrLoss; 
    // (currentPrice - buyPrice) * quantityHeld
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
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
	public LocalDate getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(LocalDate buyDate) {
		this.buyDate = buyDate;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public Double getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}
	public Double getProfitOrLoss() {
		return profitOrLoss;
	}
	public void setProfitOrLoss(Double profitOrLoss) {
		this.profitOrLoss = profitOrLoss;
	}
}
