package com.xforce.pethealth.account_management.aplication.internal.commandServices;

import com.xforce.pethealth.account_management.domain.model.commands.*;
import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import com.xforce.pethealth.account_management.domain.model.entities.Subscription;
import com.xforce.pethealth.account_management.domain.services.PetOwnerCommandService;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PetOwnerCommandServiceImpl implements PetOwnerCommandService{
    private final PetOwnerRepository petOwnerRepository;

    public PetOwnerCommandServiceImpl(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    @Transactional
    public Optional<PetOwner> handle(CreatePetOwnerCommand command) {
        if (petOwnerRepository.existsByEmail(command.email()))
            throw new IllegalArgumentException("Pet owner already exists with same email");
        PetOwner petOwner = new PetOwner(command);
        return Optional.of(petOwnerRepository.save(petOwner));
    }
    @Override
    @Transactional
    public Optional<PetOwner> handle(UpdatePetOwnerCommand command) {
        PetOwner petOwner = petOwnerRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found"));
        petOwner.setName(command.name());
        petOwner.setLastName(command.lastName());
        petOwner.setAddress(command.address());
        petOwner.setPhone(command.phone());
        petOwner.setEmail(command.email());
        petOwner.setPassword(command.password());
        petOwner.setImage(command.image());
        var updatedPetOwner = petOwnerRepository.save(petOwner);
        return Optional.of(updatedPetOwner);
    }
    @Override
    @Transactional
    public Optional<Pet> handle(AddPetCommand command) {
        PetOwner petOwner = petOwnerRepository.findById(command.petOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found"));
        Pet pet = new Pet(petOwner, command);
        petOwner.getPets().add(pet);
        petOwnerRepository.save(petOwner);
        return Optional.of(pet);
    }
    @Override
    @Transactional
    public Optional<Pet> handle(UpdatePetCommand command) {
        PetOwner petOwner = petOwnerRepository.findById(command.petOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found"));
        Pet pet = petOwner.getPets().stream()
                .filter(p -> p.getId().equals(command.petId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));

        pet.setName(command.name());
        pet.setSpecie(command.specie());
        pet.setAge(command.age());
        pet.setSex(command.sex());
        pet.setSize(command.size());
        //pet.setWeight(command.weight());
        pet.setPerimeter(command.perimeter());
        pet.setImage(command.image());

        petOwnerRepository.save(petOwner);
        return Optional.of(pet);
    }
    @Override
    @Transactional
    public void handle(DeletePetCommand command) {
        PetOwner petOwner = petOwnerRepository.findById(command.petOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found"));
        Pet pet = petOwner.getPets().stream()
                .filter(p -> p.getId().equals(command.petId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));
        petOwner.getPets().remove(pet);
        petOwnerRepository.save(petOwner);
    }
    @Override
    @Transactional
    public Optional<Subscription> handle(AddSubscriptionCommand command) {
        PetOwner petOwner = petOwnerRepository.findById(command.petOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found"));
        Subscription subscription = new Subscription(petOwner, command);
        petOwner.setSubscription(subscription);
        //subscription.setPetOwner(petOwner);
        petOwnerRepository.save(petOwner);
        return Optional.of(subscription);
    }
    @Override
    @Transactional
    public Optional<Subscription> handle(UpdateSubscriptionCommand command) {
        PetOwner petOwner = petOwnerRepository.findById(command.petOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found"));
        Subscription subscription = petOwner.getSubscription();

        subscription.setPlans(command.plans());
        subscription.setPrice(command.price());

        petOwnerRepository.save(petOwner);
        return Optional.of(subscription);
    }
}
