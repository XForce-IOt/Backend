package com.xforce.pethealth.service.Impl;

import com.xforce.pethealth.entity.HealthMetric;
import com.xforce.pethealth.repository.HealthMetricRepository;
import com.xforce.pethealth.service.HealthMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthMetricServiceImpl implements HealthMetricService {
    @Autowired
    private HealthMetricRepository healthMetricRepository;

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
