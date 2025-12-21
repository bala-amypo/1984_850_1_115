package com.example.demo.controller;

import com.example.demo.entity.AlertRecord;
import com.example.demo.service.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertRecordController {
    private final AlertService alertService;

    public AlertRecordController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<AlertRecord> triggerAlert(@RequestBody AlertRecord alert) {
        AlertRecord triggered = alertService.triggerAlert(alert);
        return ResponseEntity.ok(triggered);
    }

    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<List<AlertRecord>> getAlertsByShipment(@PathVariable Long shipmentId) {
        List<AlertRecord> alerts = alertService.getAlertsByShipment(shipmentId);
        return ResponseEntity.ok(alerts);
    }
}