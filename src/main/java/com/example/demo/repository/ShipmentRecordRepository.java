package com.example.demo.repository;

import com.example.demo.entity.ShipmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ShipmentRecordRepository extends JpaRepository<ShipmentRecord, Long> {
  Optional<ShipmentRecord> findByShipmentCode(String shipmentCode);
  List<ShipmentRecord> findAll();
}