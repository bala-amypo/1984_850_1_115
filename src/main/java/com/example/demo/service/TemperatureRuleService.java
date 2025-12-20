package com.example.demo.service;

import com.example.demo.entity.TemperatureRule;
import java.time.LocalDate;
import java.util.*;

public interface TemperatureRuleService {
  TemperatureRule createRule(TemperatureRule rule);
  Optional<TemperatureRule> getRuleForProduct(String productType, LocalDate date);
  List<TemperatureRule> getActiveRules();
}