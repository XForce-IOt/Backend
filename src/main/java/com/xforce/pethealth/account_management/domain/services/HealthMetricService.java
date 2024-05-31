package com.xforce.pethealth.account_management.domain.services;

import com.xforce.pethealth.account_management.domain.model.entities.HealthMetric;

import java.util.List;

public interface HealthMetricService {
    HealthMetric createHealthMetric(HealthMetric healthMetric);
    void updateHealthMetric(HealthMetric healthMetric);
    void deleteHealthMetric(Long id);
    List<HealthMetric> getAllHealthMetrics();
    boolean isHealthMetricExist(Long id);
}