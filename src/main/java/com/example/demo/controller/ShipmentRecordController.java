package com.example.demo.controller;

import com.example.demo.entity.ShipmentRecord;
import com.example.demo.service.ShipmentRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shipments")
public class ShipmentRecordController {
    private final ShipmentRecordService shipmentRecordService;

    public ShipmentRecordController(ShipmentRecordService shipmentRecordService) {
        this.shipmentRecordService = shipmentRecordService;
    }

    @PostMapping
    public ResponseEntity<ShipmentRecord> createShipment(@RequestBody ShipmentRecord shipment) {
        ShipmentRecord created = shipmentRecordService.createShipment(shipment);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ShipmentRecord> updateShipmentStatus(@PathVariable Long id, @RequestParam String status) {
        ShipmentRecord updated = shipmentRecordService.updateShipmentStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{code}")
    public ResponseEntity<ShipmentRecord> getShipmentByCode(@PathVariable String code) {
        Optional<ShipmentRecord> shipment = shipmentRecordService.getShipmentByCode(code);
        return shipment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ShipmentRecord>> getAllShipments() {
        List<ShipmentRecord> shipments = shipmentRecordService.getAllShipments();
        return ResponseEntity.ok(shipments);
    }
}