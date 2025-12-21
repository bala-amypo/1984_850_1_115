package com.example.demo.service.impl;

import com.example.demo.entity.BreachRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRecordRepository;
import com.example.demo.service.BreachDetectionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BreachDetectionServiceImpl implements BreachDetectionService {
    private final BreachRecordRepository breachRecordRepository;

    public BreachDetectionServiceImpl(BreachRecordRepository breachRecordRepository) {
        this.breachRecordRepository = breachRecordRepository;
    }

    @Override
    public BreachRecord logBreach(BreachRecord breach) {
        return breachRecordRepository.save(breach);
    }

    @Override
    public BreachRecord resolveBreach(Long id) {
        BreachRecord breach = breachRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Breach not found"));
        breach.setResolved(true);
        return breachRecordRepository.save(breach);
    }

    @Override
    public List<BreachRecord> getBreachesByShipment(Long shipmentId) {
        return breachRecordRepository.findByShipmentId(shipmentId);
    }
}