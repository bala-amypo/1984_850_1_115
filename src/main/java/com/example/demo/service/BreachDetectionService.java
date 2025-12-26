package com.example.demo.service;

import com.example.demo.entity.BreachRecord;

import java.util.List;

public interface BreachDetectionService {
    BreachRecord logBreach(BreachRecord breach);

    BreachRecord resolveBreach(Long id);

    List<BreachRecord> getBreachesByShipment(Long shipmentId);
}
