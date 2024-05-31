package com.xforce.pethealth.account_management.aplication.internal;

import com.xforce.pethealth.account_management.domain.model.entities.PetOwner;
import com.xforce.pethealth.account_management.domain.services.PetOwnerService;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PetOwnerServiceImpl implements PetOwnerService {
    private final PetOwnerRepository petOwnerRepository;
    public PetOwnerServiceImpl(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public List<PetOwner> getAllPetOwners() {
        return petOwnerRepository.findAll();
    }
    @Override
    public PetOwner createPetOwner(PetOwner petOwner) {
        return petOwnerRepository.save(petOwner);
    }
    @Override
    public void updatePetOwner(PetOwner petOwner) {
        petOwnerRepository.save(petOwner);
    }
    @Override
    public boolean isPetOwnerExist(Long id) {
        return petOwnerRepository.existsById(id);
    }

}