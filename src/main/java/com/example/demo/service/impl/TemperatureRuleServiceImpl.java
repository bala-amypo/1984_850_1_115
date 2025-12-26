package com.example.demo.service.impl;

import com.example.demo.entity.TemperatureRule;
import com.example.demo.repository.TemperatureRuleRepository;
import com.example.demo.service.TemperatureRuleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TemperatureRuleServiceImpl implements TemperatureRuleService {

    private final TemperatureRuleRepository temperatureRuleRepository;

    public TemperatureRuleServiceImpl(TemperatureRuleRepository temperatureRuleRepository) {
        this.temperatureRuleRepository = temperatureRuleRepository;
    }

    @Override
    public TemperatureRule createRule(TemperatureRule rule) {
        if (rule.getMinTemp() >= rule.getMaxTemp()) {
            throw new IllegalArgumentException("minTemp must be less than maxTemp");
        }
        return temperatureRuleRepository.save(rule);
    }

    @Override
    public Optional<TemperatureRule> getRuleForProduct(String productType, LocalDate date) {
        return temperatureRuleRepository.findApplicableRule(productType, date);
    }

    @Override
    public List<TemperatureRule> getActiveRules() {
        return temperatureRuleRepository.findByActiveTrue();
    }
}
