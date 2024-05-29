package com.xforce.pethealth.controller;

import com.xforce.pethealth.entity.Neck;
import com.xforce.pethealth.entity.Pet;
import com.xforce.pethealth.exception.ResourceNotFoundException;
import com.xforce.pethealth.repository.NeckRepository;
import com.xforce.pethealth.repository.PetRepository;
import com.xforce.pethealth.service.NeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/pethealth/v1")
public class NeckController {
    @Autowired
    NeckService neckService;
    @Autowired
    NeckRepository neckRepository;
    @Autowired
    PetRepository petRepository;

    @Transactional
    @PostMapping("/pets/{id}/necks")
    public ResponseEntity<Neck> createNeck(@PathVariable Long id,@RequestBody Neck neck){
        Pet pet = petRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Pet not found with id: " + id));
        if (neckService.isNeckExist(id)) { //Si el collar esta asociado con una mascota
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        neck.setPet(pet);
        neck.setTimestamp(LocalDateTime.now()); //establece el tiempo actual
        return new ResponseEntity<>(neckService.createNeck(neck), HttpStatus.CREATED);
    }

    @GetMapping("/pets/{id}/necks")
    public ResponseEntity<Neck> getNeck(@PathVariable Long id){
        if (!petRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pet not found with id: " + id);
        }
        Neck neck = neckRepository.findByPetId(id);
        if (neck == null) {
            throw new ResourceNotFoundException("Neck not found for pet with id: " + id);
        }
        return new ResponseEntity<>(neck, HttpStatus.OK);
    }

    @GetMapping("/necks/{serialNumber}")
    public ResponseEntity<Neck> getNeckBySerialNumber(@PathVariable String serialNumber){
        Neck neck = neckRepository.findBySerialNumber(serialNumber).orElseThrow(()
                -> new ResourceNotFoundException("Neck not found with serial number: " + serialNumber));
        return new ResponseEntity<>(neck, HttpStatus.OK);
    }

    @DeleteMapping("/necks/{id}")
    public ResponseEntity<Object> deleteNeck(@PathVariable("id") Long id){
        if (!neckService.isNeckExist(id)) {
            throw new ResourceNotFoundException("Neck not found with id: " + id);
        }
        neckService.deleteNeck(id);
        return new ResponseEntity<>("Deleted succesfully", HttpStatus.OK);
    }

    @PutMapping("/necks/{id}")
    public ResponseEntity<Object> updateNeck(@PathVariable("id") Long id, @RequestBody Neck neck){
        boolean isNeckExist = neckService.isNeckExist(id);
        if(isNeckExist){
            neck.setId(id);
            neckService.updateNeck(neck);
            return new ResponseEntity<>("Updated succesfully", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Neck not found with id: " + id);
        }
    }

}
