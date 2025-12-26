package com.example.demo.service.impl;

import com.example.demo.entity.TemperatureSensorLog;
import com.example.demo.repository.TemperatureSensorLogRepository;
import com.example.demo.service.TemperatureLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureLogServiceImpl implements TemperatureLogService {

    private final TemperatureSensorLogRepository temperatureSensorLogRepository;

    public TemperatureLogServiceImpl(TemperatureSensorLogRepository temperatureSensorLogRepository) {
        this.temperatureSensorLogRepository = temperatureSensorLogRepository;
    }

    @Override
    public TemperatureSensorLog recordLog(TemperatureSensorLog log) {
        return temperatureSensorLogRepository.save(log);
    }

    @Override
    public List<TemperatureSensorLog> getLogsByShipment(Long shipmentId) {
        return temperatureSensorLogRepository.findByShipmentId(shipmentId);
    }
}
