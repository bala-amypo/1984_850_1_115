package com.example.demo.repository;

import com.example.demo.entity.BreachRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreachRecordRepository extends JpaRepository<BreachRecord, Long> {
    List<BreachRecord> findByShipmentId(Long shipmentId);
}
