package com.example.demo.repository;

import com.example.demo.entity.BreachRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface BreachRecordRepository extends JpaRepository<BreachRecord, Long> {
  List<BreachRecord> findByShipmentId(Long shipmentId);
  Optional<BreachRecord> findById(Long id);
}