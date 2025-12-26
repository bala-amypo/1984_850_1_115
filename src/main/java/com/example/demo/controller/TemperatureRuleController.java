package com.example.demo.controller;

import com.example.demo.entity.TemperatureRule;
import com.example.demo.service.TemperatureRuleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rules")
public class TemperatureRuleController {

    private final TemperatureRuleService temperatureRuleService;

    public TemperatureRuleController(TemperatureRuleService temperatureRuleService) {
        this.temperatureRuleService = temperatureRuleService;
    }

    @PostMapping
    public ResponseEntity<TemperatureRule> createRule(@RequestBody TemperatureRule rule) {
        return ResponseEntity.ok(temperatureRuleService.createRule(rule));
    }

    @GetMapping("/active")
    public ResponseEntity<List<TemperatureRule>> getActiveRules() {
        return ResponseEntity.ok(temperatureRuleService.getActiveRules());
    }

    @GetMapping("/product/{productType}")
    public ResponseEntity<TemperatureRule> getRuleForProduct(
            @PathVariable String productType,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return temperatureRuleService.getRuleForProduct(productType, date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
