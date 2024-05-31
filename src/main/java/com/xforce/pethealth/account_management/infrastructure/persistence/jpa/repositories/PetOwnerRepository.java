package com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories;

import com.xforce.pethealth.account_management.domain.model.entities.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {
    boolean existsByEmail(String email);

}