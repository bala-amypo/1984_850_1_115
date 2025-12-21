package com.example.demo.repository;

import com.example.demo.entity.TemperatureRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TemperatureRuleRepository extends JpaRepository<TemperatureRule, Long> {
    List<TemperatureRule> findByActiveTrue();
    
    @Query("SELECT tr FROM TemperatureRule tr WHERE tr.productType = :productType AND tr.active = true AND :date BETWEEN tr.effectiveFrom AND tr.effectiveTo")
    Optional<TemperatureRule> findApplicableRule(@Param("productType") String productType, @Param("date") LocalDate date);
}