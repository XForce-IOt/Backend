package com.xforce.pethealth.service;

import com.xforce.pethealth.entity.HealthMetric;

import java.util.List;

public interface HealthMetricService {
    HealthMetric createHealthMetric(HealthMetric healthMetric);
    void updateHealthMetric(HealthMetric healthMetric);
    void deleteHealthMetric(Long id);
    List<HealthMetric> getAllHealthMetrics();
    boolean isHealthMetricExist(Long id);
}
