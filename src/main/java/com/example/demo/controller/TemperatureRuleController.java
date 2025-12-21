package com.example.demo.controller;

import com.example.demo.entity.TemperatureRule;
import com.example.demo.service.TemperatureRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rules")
public class TemperatureRuleController {
    private final TemperatureRuleService temperatureRuleService;

    public TemperatureRuleController(TemperatureRuleService temperatureRuleService) {
        this.temperatureRuleService = temperatureRuleService;
    }

    @PostMapping
    public ResponseEntity<TemperatureRule> createRule(@RequestBody TemperatureRule rule) {
        TemperatureRule created = temperatureRuleService.createRule(rule);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/active")
    public ResponseEntity<List<TemperatureRule>> getActiveRules() {
        List<TemperatureRule> rules = temperatureRuleService.getActiveRules();
        return ResponseEntity.ok(rules);
    }

    @GetMapping("/product/{productType}")
    public ResponseEntity<TemperatureRule> getRuleForProduct(@PathVariable String productType, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        Optional<TemperatureRule> rule = temperatureRuleService.getRuleForProduct(productType, localDate);
        return rule.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}