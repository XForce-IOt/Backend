package com.xforce.pethealth.service;

import com.xforce.pethealth.entity.PetOwner;

import java.util.List;

public interface PetOwnerService {
    PetOwner createPetOwner(PetOwner petOwnerDto);
    void updatePetOwner(PetOwner petOwnerDto);
    boolean isPetOwnerExist(Long id);
    public List<PetOwner> getAllPetOwners();
}
