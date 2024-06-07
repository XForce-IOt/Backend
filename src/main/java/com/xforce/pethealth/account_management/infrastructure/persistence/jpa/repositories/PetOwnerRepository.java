package com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories;

import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {
    boolean existsByEmail(String email);

    // Método para encontrar un Pet directamente por su ID
    @Query("SELECT p FROM PetOwner po JOIN po.pets p WHERE p.id = :petId")
    Optional<Pet> findPetById(@Param("petId") Long petId);
}