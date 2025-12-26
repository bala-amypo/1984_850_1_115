package com.example.demo.service;

import com.example.demo.entity.AlertRecord;

import java.util.List;

public interface AlertService {
    AlertRecord triggerAlert(AlertRecord alert);

    List<AlertRecord> getAlertsByShipment(Long shipmentId);
}
