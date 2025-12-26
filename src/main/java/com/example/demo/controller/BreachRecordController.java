package com.example.demo.controller;

import com.example.demo.entity.BreachRecord;
import com.example.demo.service.BreachDetectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breaches")
public class BreachRecordController {

    private final BreachDetectionService breachDetectionService;

    public BreachRecordController(BreachDetectionService breachDetectionService) {
        this.breachDetectionService = breachDetectionService;
    }

    @PostMapping
    public ResponseEntity<BreachRecord> logBreach(@RequestBody BreachRecord breach) {
        return ResponseEntity.ok(breachDetectionService.logBreach(breach));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<BreachRecord> resolveBreach(@PathVariable Long id) {
        return ResponseEntity.ok(breachDetectionService.resolveBreach(id));
    }

    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<List<BreachRecord>> getBreachesByShipment(@PathVariable Long shipmentId) {
        return ResponseEntity.ok(breachDetectionService.getBreachesByShipment(shipmentId));
    }
}
