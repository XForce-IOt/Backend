package com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories;

import com.xforce.pethealth.account_management.domain.model.entities.HealthMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthMetricRepository extends JpaRepository<HealthMetric, Long>{
    List<HealthMetric> findByNeckId(Long neckId);
}