package com.bridgelabz.repository;

import com.bridgelabz.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByUserId(long i);
    List<Alert> findByStatus(String status);
}
