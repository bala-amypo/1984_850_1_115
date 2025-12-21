package com.example.demo.repository;

import com.example.demo.entity.TemperatureSensorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TemperatureSensorLogRepository extends JpaRepository<TemperatureSensorLog, Long> {
    List<TemperatureSensorLog> findByShipmentId(Long shipmentId);
}