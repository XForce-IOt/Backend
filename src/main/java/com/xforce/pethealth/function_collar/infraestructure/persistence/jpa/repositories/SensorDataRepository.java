package com.xforce.pethealth.function_collar.infraestructure.persistence.jpa.repositories;

import com.xforce.pethealth.function_collar.domain.model.entities.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    List<SensorData> findAllByPetId(Long petId);
}
