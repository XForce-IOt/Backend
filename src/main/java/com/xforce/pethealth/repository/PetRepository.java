package com.xforce.pethealth.repository;

import com.xforce.pethealth.entity.Pet;
import com.xforce.pethealth.entity.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByPetOwner(PetOwner petOwner);
    List<Pet> findByName(String name);
}
