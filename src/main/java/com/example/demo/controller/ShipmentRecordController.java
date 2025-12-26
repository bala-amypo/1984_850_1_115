package com.example.demo.controller;

import com.example.demo.entity.ShipmentRecord;
import com.example.demo.service.ShipmentRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipments")
public class ShipmentRecordController {

    private final ShipmentRecordService shipmentRecordService;

    public ShipmentRecordController(ShipmentRecordService shipmentRecordService) {
        this.shipmentRecordService = shipmentRecordService;
    }

    @PostMapping
    public ResponseEntity<ShipmentRecord> createShipment(@RequestBody ShipmentRecord shipment) {
        return ResponseEntity.ok(shipmentRecordService.createShipment(shipment));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ShipmentRecord> updateStatus(@PathVariable Long id, @RequestBody String status) {
        return ResponseEntity.ok(shipmentRecordService.updateShipmentStatus(id, status));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ShipmentRecord> getByCode(@PathVariable String code) {
        return shipmentRecordService.getShipmentByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ShipmentRecord>> getAllShipments() {
        return ResponseEntity.ok(shipmentRecordService.getAllShipments());
    }
}
