package com.xforce.pethealth.repository;

import com.xforce.pethealth.entity.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {
    boolean existsByEmail(String email);

}
