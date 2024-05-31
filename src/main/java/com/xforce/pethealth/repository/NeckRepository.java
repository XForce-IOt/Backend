package com.xforce.pethealth.repository;

import com.xforce.pethealth.entity.Neck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface NeckRepository extends JpaRepository<Neck, Long> {
    Optional<Neck> findBySerialNumber(String serialNumber);
    Neck findByPetId(Long petId);
}
