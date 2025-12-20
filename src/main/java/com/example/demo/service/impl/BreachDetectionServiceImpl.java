package com.example.demo.service.impl;

import com.example.demo.entity.BreachRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BreachRecordRepository;
import com.example.demo.service.BreachDetectionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BreachDetectionServiceImpl implements BreachDetectionService {
  private final BreachRecordRepository repo;
  public BreachDetectionServiceImpl(BreachRecordRepository repo) { this.repo = repo; }

  @Override public BreachRecord logBreach(BreachRecord breach) { return repo.save(breach); }

  @Override public BreachRecord resolveBreach(Long id) {
    BreachRecord br = repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Breach not found"));
    br.setResolved(true);
    return repo.save(br);
  }

  @Override public List<BreachRecord> getBreachesByShipment(Long shipmentId) { return repo.findByShipmentId(shipmentId); }
}