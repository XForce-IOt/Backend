package com.xforce.pethealth.controller;

import com.xforce.pethealth.entity.Pet;
import com.xforce.pethealth.entity.PetOwner;
import com.xforce.pethealth.exception.ResourceNotFoundException;
import com.xforce.pethealth.exception.ValidationException;
import com.xforce.pethealth.repository.PetOwnerRepository;
import com.xforce.pethealth.repository.PetRepository;
import com.xforce.pethealth.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pethealth/v1")
public class PetController {
    @Autowired
    private PetService petService;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetOwnerRepository petOwnerRepository;


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
    public ResponseEntity<Pet> createPet(@PathVariable Long id, @RequestBody Pet pet){
        PetOwner petOwner = petOwnerRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Pet Owner not found with id: " + id));
        validatePet(pet);
        pet.setPetOwner(petOwner);
        return new ResponseEntity<>(petService.createPet(pet), HttpStatus.CREATED);
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
