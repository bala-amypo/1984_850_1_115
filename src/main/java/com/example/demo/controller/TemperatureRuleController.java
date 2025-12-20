package com.example.demo.controller;

import com.example.demo.entity.TemperatureRule;
import com.example.demo.service.TemperatureRuleService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/rules")
public class TemperatureRuleController {
  private final TemperatureRuleService service;
  public TemperatureRuleController(TemperatureRuleService service) { this.service = service; }

  @PostMapping
  public TemperatureRule create(@RequestBody TemperatureRule rule) { return service.createRule(rule); }

  @GetMapping("/active")
  public List<TemperatureRule> active() { return service.getActiveRules(); }

  @GetMapping("/product/{productType}")
  public Optional<TemperatureRule> byProduct(@PathVariable String productType,
                                             @RequestParam("date") String date) {
    return service.getRuleForProduct(productType, LocalDate.parse(date));
  }
}