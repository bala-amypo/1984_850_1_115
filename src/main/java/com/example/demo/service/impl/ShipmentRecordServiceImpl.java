package com.example.demo.service.impl;

import com.example.demo.entity.ShipmentRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ShipmentRecordRepository;
import com.example.demo.service.ShipmentRecordService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ShipmentRecordServiceImpl implements ShipmentRecordService {
    private final ShipmentRecordRepository repo;

    public ShipmentRecordServiceImpl(ShipmentRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public ShipmentRecord createShipment(ShipmentRecord shipment) {
        return repo.save(shipment);
    }

    @Override
    public ShipmentRecord updateShipmentStatus(Long id, String newStatus) {
        ShipmentRecord sr = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
        sr.setStatus(newStatus);
        return repo.save(sr);
    }

    @Override
    public Optional<ShipmentRecord> getShipmentByCode(String code) {
        return repo.findByShipmentCode(code);
    }

    @Override
    public List<ShipmentRecord> getAllShipments() {
        return repo.findAll();
    }
}