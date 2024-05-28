package com.xforce.pethealth.repository;

import com.xforce.pethealth.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByPetOwnerId(Long petOwnerId);
    Pet findByNeckId(Long neckId);
}
