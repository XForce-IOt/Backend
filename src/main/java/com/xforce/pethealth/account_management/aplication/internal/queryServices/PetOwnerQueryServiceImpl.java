package com.xforce.pethealth.account_management.aplication.internal.queryServices;

import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import com.xforce.pethealth.account_management.domain.model.entities.Subscription;
import com.xforce.pethealth.account_management.domain.model.queries.*;
import com.xforce.pethealth.account_management.domain.services.PetOwnerQueryService;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetOwnerQueryServiceImpl implements PetOwnerQueryService {
    private final PetOwnerRepository petOwnerRepository;

    public PetOwnerQueryServiceImpl(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public List<PetOwner> handle(GetAllPetOwnersQuery query) {
        return petOwnerRepository.findAll();
    }
    @Override
    public Optional<PetOwner> handle(GetPetOwnerByIdQuery query) {
        return petOwnerRepository.findById(query.id());
    }
    @Override
    public List<Pet> handle(GetAllPetsQuery query){
        return petOwnerRepository.findById(query.petOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found"))
                .getPets();
    }
    @Override
    public Pet handle(GetPetByIdQuery query){
        return petOwnerRepository.findById(query.petOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found"))
                .getPets().stream()
                .filter(p -> p.getId().equals(query.petId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));
    }
    @Override
    public List<Pet> handle(GetAllPetsByNameQuery query){
        return petOwnerRepository.findById(query.petOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found"))
                .getPets().stream()
                .filter(p -> p.getName().equals(query.name()))
                .toList();
    }
    @Override
    public Subscription handle(GetSubscriptionByIdQuery query){
        return petOwnerRepository.findById(query.petOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found"))
                .getSubscription();
    }
}
