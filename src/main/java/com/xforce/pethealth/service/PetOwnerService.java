package com.xforce.pethealth.service;

import com.xforce.pethealth.dto.PetOwnerDto;

import java.util.List;

public interface PetOwnerService {
    PetOwnerDto createPetOwner(PetOwnerDto petOwnerDto);
    void updatePetOwner(PetOwnerDto petOwnerDto);
    void deletePetOwner(Long id);
    PetOwnerDto getPetOwner(Long id);
    boolean isPetOwnerExist(Long id);
    public List<PetOwnerDto> getAllPetOwners();
}
