package com.xforce.pethealth.service.Impl;

import com.xforce.pethealth.entity.Pet;
import com.xforce.pethealth.repository.PetRepository;
import com.xforce.pethealth.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }
    @Override
    public void updatePet(Pet pet) {
        petRepository.save(pet);
    }
    @Override
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
    @Override
    public boolean isPetExist(Long id) {
        return petRepository.existsById(id);
    }
    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
}
