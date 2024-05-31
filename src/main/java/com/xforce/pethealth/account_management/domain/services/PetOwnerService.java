package com.xforce.pethealth.account_management.domain.services;

import com.xforce.pethealth.account_management.domain.model.entities.PetOwner;

import java.util.List;

public interface PetOwnerService {
    PetOwner createPetOwner(PetOwner petOwnerDto);
    void updatePetOwner(PetOwner petOwnerDto);
    boolean isPetOwnerExist(Long id);
    List<PetOwner> getAllPetOwners();
}