package com.xforce.pethealth.service.Impl;

import com.xforce.pethealth.entity.PetOwner;
import com.xforce.pethealth.repository.PetOwnerRepository;
import com.xforce.pethealth.service.PetOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PetOwnerServiceImpl implements PetOwnerService {
    private final PetOwnerRepository petOwnerRepository;

    @Autowired
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
