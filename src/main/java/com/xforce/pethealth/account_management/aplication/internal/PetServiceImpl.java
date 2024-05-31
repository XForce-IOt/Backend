package com.xforce.pethealth.account_management.aplication.internal;

import com.xforce.pethealth.account_management.domain.model.entities.Neck;
import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import com.xforce.pethealth.account_management.domain.model.entities.PetOwner;
import com.xforce.pethealth.account_management.domain.services.PetService;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.NeckRepository;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final NeckRepository neckRepository;
    private final PetOwnerRepository petOwnerRepository;

    public PetServiceImpl(PetRepository petRepository, NeckRepository neckRepository, PetOwnerRepository petOwnerRepository) {
        this.petRepository = petRepository;
        this.neckRepository = neckRepository;
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public Pet createPet(Long petOwnerId, Long neckId, Pet pet) {
        PetOwner petOwner = petOwnerRepository.findById(petOwnerId)
                .orElseThrow(() -> new RuntimeException("PetOwner not found"));

        Neck neck = neckRepository.findById(neckId)
                .orElseThrow(() -> new RuntimeException("Neck not found"));

        if (petRepository.existsByNeckId(neckId)){
            throw new RuntimeException("This neck is already assigned to another pet.");
        }
        neck.setPet(pet);
        pet.setPetOwner(petOwner);
        pet.setNeck(neck);


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
    public Pet getPet(Long id) {
        return petRepository.findById(id).get();
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
