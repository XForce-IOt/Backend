package com.xforce.pethealth.repository;

import com.xforce.pethealth.entity.HealthMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthMetricRepository extends JpaRepository<HealthMetric, Long>{
    List<HealthMetric> findByNeckId(Long neckId);
}