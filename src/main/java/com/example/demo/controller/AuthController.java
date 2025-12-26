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
        BreachRecord logged = breachDetectionService.logBreach(breach);
        return ResponseEntity.ok(logged);
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<BreachRecord> resolveBreach(@PathVariable Long id) {
        BreachRecord resolved = breachDetectionService.resolveBreach(id);
        return ResponseEntity.ok(resolved);
    }

    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<List<BreachRecord>> getBreachesByShipment(@PathVariable Long shipmentId) {
        List<BreachRecord> breaches = breachDetectionService.getBreachesByShipment(shipmentId);
        return ResponseEntity.ok(breaches);
    }
}