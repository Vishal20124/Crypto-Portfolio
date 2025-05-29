package com.bridgelabz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.entity.CryptoPrice;

public interface CryptoPriceRepository extends JpaRepository<CryptoPrice, String> {
}
