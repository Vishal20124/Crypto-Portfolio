package com.bridgelabz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.entity.CryptoHolding;

import java.util.List;

public interface CryptoHoldingRepository extends JpaRepository<CryptoHolding, Long> {

    List<CryptoHolding> findByUserId(Long userId);
}
