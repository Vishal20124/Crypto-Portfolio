package com.bridgelabz.PortfolioAPIs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class CryptoHolding {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private int userId;
	@Column
	private String coinName;
	@Column
	private String symbol;
	@Column
	private float quantityHeld;
	@Column
	private float buyPrice;
	@Column
	private int buyDate;
	
	public CryptoHolding() {
		
	}
	
	public CryptoHolding(int userId, String coinName, String symbol, float quantityHeld, float buyPrice, int buyDate) {
		super();
		this.userId = userId;
		this.coinName = coinName;
		this.symbol = symbol;
		this.quantityHeld = quantityHeld;
		this.buyPrice = buyPrice;
		this.buyDate = buyDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public float getQuantityHeld() {
		return quantityHeld;
	}
	public void setQuantityHeld(float quantityHeld) {
		this.quantityHeld = quantityHeld;
	}
	public float getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}
	public int getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(int buyDate) {
		this.buyDate = buyDate;
	}
	@Override
	public String toString() {
		return "CryptoHolding [id=" + id + ", userId=" + userId + ", coinName=" + coinName + ", symbol=" + symbol
				+ ", quantityHeld=" + quantityHeld + ", buyPrice=" + buyPrice + ", buyDate=" + buyDate + "]";
	}
}

