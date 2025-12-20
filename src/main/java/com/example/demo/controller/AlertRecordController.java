package com.example.demo.controller;

import com.example.demo.entity.AlertRecord;
import com.example.demo.service.AlertService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertRecordController {
  private final AlertService service;
  public AlertRecordController(AlertService service) { this.service = service; }

  @PostMapping
  public AlertRecord trigger(@RequestBody AlertRecord ar) { return service.triggerAlert(ar); }

  @GetMapping("/shipment/{shipmentId}")
  public List<AlertRecord> byShipment(@PathVariable Long shipmentId) { return service.getAlertsByShipment(shipmentId); }
}