package com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories;

import com.xforce.pethealth.account_management.domain.model.entities.Neck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface NeckRepository extends JpaRepository<Neck, Long> {
    Optional<Neck> findBySerialNumber(String serialNumber);
    Neck findByPetId(Long petId);
    boolean existsBySerialNumber(String serialNumber);
}