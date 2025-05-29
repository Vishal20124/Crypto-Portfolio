package com.bridgelabz.PortfolioAPIs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class CryptoPrice {
	@Id
	private int symbol;
	@Column
	private int currentPrice;
	@Column
	private int timestamp;
	
	public CryptoPrice() {
		
	}
	public CryptoPrice(int symbol, int currentPrice, int timestamp) {
		super();
		this.symbol = symbol;
		this.currentPrice = currentPrice;
		this.timestamp = timestamp;
	}

	public int getSymbol() {
		return symbol;
	}
	public void setSymbol(int symbol) {
		this.symbol = symbol;
	}
	public int getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "CryptoPrice [symbol=" + symbol + ", currentPrice=" + currentPrice + ", timestamp=" + timestamp + "]";
	}
	

}
