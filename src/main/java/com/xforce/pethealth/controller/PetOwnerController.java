package com.xforce.pethealth.controller;

import com.xforce.pethealth.dto.PetOwnerDto;
import com.xforce.pethealth.exception.ValidationException;
import com.xforce.pethealth.repository.PetOwnerRepository;
import com.xforce.pethealth.service.PetOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pethealth/v1")
public class PetOwnerController {

    @Autowired
    private PetOwnerService petOwnerService;
    private final PetOwnerRepository petOwnerRepository;
    public PetOwnerController(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }
    @GetMapping("/pet-owners")
    public List<PetOwnerDto> getAllPetOwners(){
        return petOwnerService.getAllPetOwners();
    }
    @PostMapping("/pet-owners")
    public ResponseEntity<PetOwnerDto> createPetOwner(@RequestBody PetOwnerDto petOwnerDto){
        existsByEmail(petOwnerDto);
        validatePetOwner(petOwnerDto);
        return new ResponseEntity<>(petOwnerService.createPetOwner(petOwnerDto), HttpStatus.CREATED);
    }
    @PutMapping("/pet-owners/{id}")
    public ResponseEntity<Object> updatePetOwner(@PathVariable("id") Long id, @RequestBody PetOwnerDto petOwnerDto){
        boolean isPetOwnerExist = petOwnerService.isPetOwnerExist(id);
        if(isPetOwnerExist){
            validatePetOwner(petOwnerDto);
            petOwnerDto.setId(id);
            petOwnerService.updatePetOwner(petOwnerDto);
            return new ResponseEntity<>("Updated succesfully", HttpStatus.OK);
        } else {
            throw new ValidationException("Data were not updated");
        }
    }

    public void validatePetOwner(PetOwnerDto petOwnerDto){
        if(petOwnerDto.getName()== null || petOwnerDto.getName().trim().isEmpty()){
            throw new ValidationException("El nombre del dueño de la mascota es obligatorio");
        }
        if(petOwnerDto.getLastName()== null || petOwnerDto.getLastName().trim().isEmpty()){
            throw new ValidationException("El apellido del dueño de la mascota es obligatorio");
        }
        if(petOwnerDto.getAddress()== null || petOwnerDto.getAddress().trim().isEmpty()){
            throw new ValidationException("La dirección del dueño de la mascota es obligatoria");
        }
        if(petOwnerDto.getPhone()== null || petOwnerDto.getPhone().trim().isEmpty()){
            throw new ValidationException("El teléfono del dueño de la mascota es obligatorio");
        }
        if(petOwnerDto.getEmail()== null || petOwnerDto.getEmail().trim().isEmpty()){
            throw new ValidationException("El email del dueño de la mascota es obligatorio");
        }
        if(petOwnerDto.getPassword()== null || petOwnerDto.getPassword().trim().isEmpty()){
            throw new ValidationException("La contraseña del dueño de la mascota es obligatoria");
        }
    }

    public void existsByEmail(PetOwnerDto petOwnerDto){
        if(petOwnerRepository.existsByEmail(petOwnerDto.getEmail())){
            throw new ValidationException("El email ya se encuentra registrado");
        }
    }

}
