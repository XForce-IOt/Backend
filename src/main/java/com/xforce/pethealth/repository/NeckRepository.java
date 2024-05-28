package com.xforce.pethealth.repository;

import com.xforce.pethealth.entity.Neck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NeckRepository extends JpaRepository<Neck, Long> {
    Boolean existsBySerialNumber(String serialNumber);
    Neck findByPetId(Long petId);
}
