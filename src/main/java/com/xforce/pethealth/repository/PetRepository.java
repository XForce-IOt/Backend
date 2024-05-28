package com.xforce.pethealth.repository;

import com.xforce.pethealth.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
