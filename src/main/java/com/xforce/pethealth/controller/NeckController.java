package com.xforce.pethealth.controller;

import com.xforce.pethealth.entity.Neck;
import com.xforce.pethealth.entity.Pet;
import com.xforce.pethealth.exception.ResourceNotFoundException;
import com.xforce.pethealth.repository.PetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/pethealth/v1")
public class NeckRepository {
    NeckRepository neckRepository;
    PetRepository petRepository;

    public NeckRepository(NeckRepository neckRepository, PetRepository petRepository) {
        this.neckRepository = neckRepository;
        this.petRepository = petRepository;
    }

    @Transactional
    @PostMapping("/pets/{id}/neck")
    public ResponseEntity<Neck> createNeck(@PathVariable Long id,@RequestBody Neck neck){
        Pet pet = petRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Pet not found with id: " + id));
        neck.setPet(pet);
        neck.setTimestamp(LocalDateTime.now()); //establece el tiempo actual


        return new ResponseEntity<Neck>(neckRepository.save(neck), HttpStatus.CREATED);
    }
}
