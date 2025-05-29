package com.bridgelabz.PortfolioAPIs.Repository;

import com.bridgelabz.PortfolioAPIs.entity.CryptoPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, Integer> {
}

