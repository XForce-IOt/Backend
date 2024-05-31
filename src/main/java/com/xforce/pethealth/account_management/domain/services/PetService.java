package com.xforce.pethealth.account_management.domain.services;

import com.xforce.pethealth.account_management.domain.model.entities.Pet;

import java.util.List;

public interface PetService {
    Pet createPet(Long petOwnerId, Long neckId, Pet pet);
    void updatePet(Pet pet);
    void deletePet(Long id);
    List<Pet> getAllPets();
    Pet getPet(Long id);
    boolean isPetExist(Long id);
}