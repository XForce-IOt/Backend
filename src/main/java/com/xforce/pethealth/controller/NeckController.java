package com.xforce.pethealth.controller;

import com.xforce.pethealth.entity.Neck;
import com.xforce.pethealth.entity.Pet;
import com.xforce.pethealth.exception.ResourceNotFoundException;
import com.xforce.pethealth.repository.NeckRepository;
import com.xforce.pethealth.repository.PetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/pethealth/v1")
public class NeckController {
    NeckRepository neckRepository;
    PetRepository petRepository;

    public NeckController(NeckRepository neckRepository, PetRepository petRepository) {
        this.neckRepository = neckRepository;
        this.petRepository = petRepository;
    }

    @Transactional
    @PostMapping("/pets/{id}/necks")
    public ResponseEntity<Neck> createNeck(@PathVariable Long id,@RequestBody Neck neck){
        Pet pet = petRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Pet not found with id: " + id));
        if (neckRepository.existsByPetId(id)) { //Si el collar esta asociado con una mascota
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        neck.setPet(pet);
        neck.setTimestamp(LocalDateTime.now()); //establece el tiempo actual
        return new ResponseEntity<Neck>(neckRepository.save(neck), HttpStatus.CREATED);
    }

    @GetMapping("/pets/{id}/necks")
    public ResponseEntity<Neck> getNeck(@PathVariable Long id){
        if (!petRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pet not found with id: " + id);
        }
        Neck neck = neckRepository.findByPetId(id);
        return new ResponseEntity<Neck>(neck, HttpStatus.OK);
    }

    @GetMapping("/necks/{serialNumber}")
    public ResponseEntity<Neck> getNeckBySerialNumber(@PathVariable String serialNumber){
        Neck neck = neckRepository.findBySerialNumber(serialNumber).orElseThrow(()
                -> new ResourceNotFoundException("Neck not found with serial number: " + serialNumber));
        return new ResponseEntity<Neck>(neck, HttpStatus.OK);
    }

    @DeleteMapping("/necks/{id}")
    public ResponseEntity<Object> deleteNeck(@PathVariable Long id){
        if (!neckRepository.existsById(id)) {
            throw new ResourceNotFoundException("Neck not found with id: " + id);
        }
        neckRepository.deleteById(id);
        return new ResponseEntity<>("Deleted succesfully", HttpStatus.OK);
    }

    @PutMapping("/necks/{id}")
    public ResponseEntity<Neck> updateNeck(@PathVariable Long id, @RequestBody Neck neck){
        Neck neckToUpdate = neckRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Neck not found with id: " + id));
        neckToUpdate.setSerialNumber(neckToUpdate.getSerialNumber());
        neckToUpdate.setHeartRate(neck.getHeartRate());
        neckToUpdate.setPhysicalActivity(neck.getPhysicalActivity());
        neckToUpdate.setTemperature(neck.getTemperature());
        neckToUpdate.setSleepQuality(neck.getSleepQuality());
        neckToUpdate.setHydrationLevel(neck.getHydrationLevel());
        neckToUpdate.setLatitude(neck.getLatitude());
        neckToUpdate.setLongitude(neck.getLongitude());
        neckToUpdate.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<Neck>(neckRepository.save(neckToUpdate), HttpStatus.OK);
    }

}
