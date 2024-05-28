package com.xforce.pethealth.service;

import com.xforce.pethealth.entity.Pet;

import java.util.List;

public interface PetService {
    Pet createPet(Pet pet);
    void updatePet(Pet pet);
    void deletePet(Long id);
    List<Pet> getAllPets();
    boolean isPetExist(Long id);
}
