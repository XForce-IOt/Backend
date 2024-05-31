package com.xforce.pethealth.account_management.interfaces.rest;

import com.xforce.pethealth.account_management.domain.model.entities.Neck;
import com.xforce.pethealth.account_management.domain.services.NeckService;
import com.xforce.pethealth.account_management.infrastructure.exception.ResourceNotFoundException;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.NeckRepository;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pethealth/v1")
public class NeckController {
    private final NeckService neckService;
    private final NeckRepository neckRepository;
    private final PetRepository petRepository;
    public NeckController(NeckService neckService, NeckRepository neckRepository, PetRepository petRepository) {
        this.neckService = neckService;
        this.neckRepository = neckRepository;
        this.petRepository = petRepository;
    }

    @Transactional
    @PostMapping("/necks")
    public ResponseEntity<Neck> createNeck(@RequestBody Neck neck){
        if(neckRepository.existsBySerialNumber(neck.getSerialNumber())){
            throw new ResourceNotFoundException("Neck already exists with serial number: " + neck.getSerialNumber());
        }
        return new ResponseEntity<>(neckService.createNeck(neck), HttpStatus.CREATED);
    }
    @GetMapping("/necks")
    public ResponseEntity<Object> getAllNecks(){
        return new ResponseEntity<>(neckService.getAllNecks(), HttpStatus.OK);
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
