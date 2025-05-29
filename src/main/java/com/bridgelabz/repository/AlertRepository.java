package com.bridgelabz.repository;

import com.bridgelabz.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByUserId(Long userId); // working query

    List<Alert> findByStatus(String status); // ✅ FIXED: Accepts "pending" or "triggered"
}
