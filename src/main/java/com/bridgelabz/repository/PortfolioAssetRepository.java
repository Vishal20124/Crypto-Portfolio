package com.bridgelabz.repository;

import com.bridgelabz.entity.PortfolioAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioAssetRepository extends JpaRepository<PortfolioAsset, Long> {
}
