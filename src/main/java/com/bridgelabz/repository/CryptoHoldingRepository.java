package com.bridgelabz.PortfolioAPIs.entity;

import com.bridgelabz.PortfolioAPIs.entity.CryptoHolding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoHoldingRepository extends JpaRepository<CryptoHolding, Integer> {
}
