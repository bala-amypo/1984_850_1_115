package com.example.demo.controller;

import com.example.demo.entity.ShipmentRecord;
import com.example.demo.service.ShipmentRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shipments")
public class ShipmentRecordController {
    private final ShipmentRecordService service;

    public ShipmentRecordController(ShipmentRecordService service) {
        this.service = service;
    }

    @PostMapping
    public ShipmentRecord create(@RequestBody ShipmentRecord sr) {
        return service.createShipment(sr);
    }

    @PutMapping("/{id}/status")
    public ShipmentRecord updateStatus(@PathVariable Long id, @RequestParam String status) {
        return service.updateShipmentStatus(id, status);
    }

    @GetMapping("/{code}")
    public Optional<ShipmentRecord> getByCode(@PathVariable String code) {
        return service.getShipmentByCode(code);
    }

    @GetMapping
    public List<ShipmentRecord> all() {
        return service.getAllShipments();
    }
}