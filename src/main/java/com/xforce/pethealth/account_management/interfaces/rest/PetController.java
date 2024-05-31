package com.xforce.pethealth.account_management.interfaces.rest;

import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import com.xforce.pethealth.account_management.domain.model.entities.PetOwner;
import com.xforce.pethealth.account_management.domain.services.PetService;
import com.xforce.pethealth.account_management.infrastructure.exception.ResourceNotFoundException;
import com.xforce.pethealth.account_management.infrastructure.exception.ValidationException;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pethealth/v1")
public class PetController {
    private final PetService petService;
    private final PetRepository petRepository;
    private final PetOwnerRepository petOwnerRepository;
    public PetController(PetService petService, PetRepository petRepository, PetOwnerRepository petOwnerRepository) {
        this.petService = petService;
        this.petRepository = petRepository;
        this.petOwnerRepository = petOwnerRepository;
    }


    @Transactional(readOnly = true)
    @GetMapping("/pets/filterByName")
    public ResponseEntity<List<Pet>> getPetByName(@RequestParam String name){
        return new ResponseEntity<>(petRepository.findByName(name), HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    @GetMapping("/pet-owner/{id}/pets")
    public ResponseEntity<List<Pet>> getPetsByPetOwnerId(@PathVariable Long id){
        PetOwner petOwner = petOwnerRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Pet Owner not found with id: " + id));
        return new ResponseEntity<>(petRepository.findByPetOwner(petOwner), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/pet-owner/{id}/pets")
    public ResponseEntity<Pet> createPet(@PathVariable Long id, @RequestParam Long neckId, @RequestBody Pet pet){

        return new ResponseEntity<>(petService.createPet(id,neckId,pet), HttpStatus.CREATED);
    }
    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Long id){
        Pet pet = petRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Pet not found with id: " + id));
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }
    @PutMapping("/pets/{id}")
    public ResponseEntity<Object> updatePet(@PathVariable Long id, @RequestBody Pet pet){
        boolean isPetExist = petService.isPetExist(id);
        if(isPetExist){
            validatePet(pet);
            pet.setId(id);
            petService.updatePet(pet);
            return new ResponseEntity<>("Updated succesfully", HttpStatus.OK);
        } else {
            throw new ValidationException("Data were not updated");
        }
    }
    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Object> deletePet(@PathVariable Long id){
        if(petService.isPetExist(id)){
            petService.deletePet(id);
            return new ResponseEntity<>("Deleted succesfully", HttpStatus.OK);
        } else {
            throw new ValidationException("Data were not deleted");
        }
    }
    private void validatePet(Pet pet){
        if(pet.getName()== null || pet.getName().trim().isEmpty()){
            throw new ValidationException("Pet name is required");
        }
        if(pet.getSpecie()== null || pet.getSpecie().trim().isEmpty()){
            throw new ValidationException("Pet specie is required");
        }
        if(pet.getAge() <= 0){
            throw new ValidationException("The pet's age must be greater than 0");
        }
        if(pet.getSex()== null || pet.getSex().trim().isEmpty()){
            throw new ValidationException("Pet sex is required");
        }
        if(pet.getSize()== null || pet.getSize().trim().isEmpty()){
            throw new ValidationException("Pet size is required");
        }
        if(pet.getWeight() <= 0){
            throw new ValidationException("The pet's weight must be greater than 0");
        }
        if(pet.getPerimeter() <= 0){
            throw new ValidationException("The pet's perimeter must be greater than 0");
        }
    }
}
