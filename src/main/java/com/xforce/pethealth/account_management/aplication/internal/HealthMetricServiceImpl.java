package com.xforce.pethealth.account_management.aplication.internal;

import com.xforce.pethealth.account_management.domain.model.entities.HealthMetric;
import com.xforce.pethealth.account_management.domain.services.HealthMetricService;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.HealthMetricRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthMetricServiceImpl implements HealthMetricService {
    private final HealthMetricRepository healthMetricRepository;
    public HealthMetricServiceImpl(HealthMetricRepository healthMetricRepository) {
        this.healthMetricRepository = healthMetricRepository;
    }

    @Override
    public HealthMetric createHealthMetric(HealthMetric healthMetric) {
        return healthMetricRepository.save(healthMetric);
    }
    @Override
    public void updateHealthMetric(HealthMetric healthMetric) {
        healthMetricRepository.save(healthMetric);
    }
    @Override
    public void deleteHealthMetric(Long id) {
        healthMetricRepository.deleteById(id);
    }
    @Override
    public boolean isHealthMetricExist(Long id) {
        return healthMetricRepository.existsById(id);
    }
    @Override
    public List<HealthMetric> getAllHealthMetrics() {
        return healthMetricRepository.findAll();
    }
}