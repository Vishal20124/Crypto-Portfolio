package com.bridgelabz.entity;

import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "portfolio_assets")

public class PortfolioAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String coinName;

    private String symbol;

    private double quantityHeld;

    private double buyPrice;

    private LocalDate buyDate;

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
    
}
