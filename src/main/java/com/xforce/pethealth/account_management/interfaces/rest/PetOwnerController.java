package com.xforce.pethealth.account_management.interfaces.rest;

import com.xforce.pethealth.account_management.domain.model.entities.PetOwner;
import com.xforce.pethealth.account_management.domain.services.PetOwnerService;
import com.xforce.pethealth.account_management.infrastructure.exception.ValidationException;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pethealth/v1")
public class PetOwnerController {
    private final PetOwnerService petOwnerService;
    private final PetOwnerRepository petOwnerRepository;
    public PetOwnerController(PetOwnerService petOwnerService, PetOwnerRepository petOwnerRepository) {
        this.petOwnerService = petOwnerService;
        this.petOwnerRepository = petOwnerRepository;
    }

    @GetMapping("/pet-owners")
    public List<PetOwner> getAllPetOwners(){
        return petOwnerService.getAllPetOwners();
    }
    @PostMapping("/pet-owners")
    public ResponseEntity<PetOwner> createPetOwner(@RequestBody PetOwner petOwner){
        existsByEmail(petOwner);
        validatePetOwner(petOwner);
        return new ResponseEntity<>(petOwnerService.createPetOwner(petOwner), HttpStatus.CREATED);
    }
    @PutMapping("/pet-owners/{id}")
    public ResponseEntity<Object> updatePetOwner(@PathVariable("id") Long id, @RequestBody PetOwner petOwner){
        if (!id.equals(petOwner.getId())) {
            throw new ValidationException("El ID del dueño de mascota en la URL no coincide con el ID proporcionado en el cuerpo de la solicitud");
        }

        validatePetOwner(petOwner);
        if(petOwnerService.isPetOwnerExist(id)){
            petOwnerService.updatePetOwner(petOwner);
            return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
        } else {
            throw new ValidationException("El dueño de la mascota con ID " + id + " no existe");
        }
    }

    public void validatePetOwner(PetOwner petOwner){
        if(petOwner.getName()== null || petOwner.getName().trim().isEmpty()){
            throw new ValidationException("El nombre del dueño de la mascota es obligatorio");
        }
        if(petOwner.getLastName()== null || petOwner.getLastName().trim().isEmpty()){
            throw new ValidationException("El apellido del dueño de la mascota es obligatorio");
        }
        if(petOwner.getAddress()== null || petOwner.getAddress().trim().isEmpty()){
            throw new ValidationException("La dirección del dueño de la mascota es obligatoria");
        }
        if(petOwner.getPhone()== null || petOwner.getPhone().trim().isEmpty()){
            throw new ValidationException("El teléfono del dueño de la mascota es obligatorio");
        }
        if(petOwner.getEmail()== null || petOwner.getEmail().trim().isEmpty()){
            throw new ValidationException("El email del dueño de la mascota es obligatorio");
        }
        if(petOwner.getPassword()== null || petOwner.getPassword().trim().isEmpty()){
            throw new ValidationException("La contraseña del dueño de la mascota es obligatoria");
        }
    }

    public void existsByEmail(PetOwner petOwner){
        if(petOwnerRepository.existsByEmail(petOwner.getEmail())){
            throw new ValidationException("El email ya se encuentra registrado");
        }
    }

}
