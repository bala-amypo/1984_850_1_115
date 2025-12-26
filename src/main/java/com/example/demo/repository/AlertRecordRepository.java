package com.example.demo.repository;

import com.example.demo.entity.AlertRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRecordRepository extends JpaRepository<AlertRecord, Long> {
    List<AlertRecord> findByShipmentId(Long shipmentId);
}
