package com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories;

import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import com.xforce.pethealth.account_management.domain.model.entities.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByPetOwner(PetOwner petOwner);
    List<Pet> findByName(String name);
    boolean existsByNeckId(Long id);
}