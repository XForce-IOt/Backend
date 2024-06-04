package com.xforce.pethealth.account_management.interfaces.rest;

import com.xforce.pethealth.account_management.domain.model.entities.PetOwner;
import com.xforce.pethealth.account_management.domain.model.entities.Subscription;
import com.xforce.pethealth.account_management.domain.services.SubscriptionService;
import com.xforce.pethealth.account_management.infrastructure.exception.ResourceNotFoundException;
import com.xforce.pethealth.account_management.infrastructure.exception.ValidationException;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pethealth/v1")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;
    private final SubscriptionRepository subscriptionRepository;
    private final PetOwnerRepository petOwnerRepository;
    public SubscriptionController(SubscriptionRepository subscriptionRepository, PetOwnerRepository petOwnerRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.petOwnerRepository = petOwnerRepository;
    }

    @Transactional(readOnly = true)
    @GetMapping("/pet-owners/{id}/subscriptions")
    public ResponseEntity<List<Subscription>> getSubscriptionsByPetOwnerId(@PathVariable Long id){
        PetOwner petOwner = petOwnerRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Pet Owner not found with id: " + id));
        return new ResponseEntity<>(subscriptionRepository.findByPetOwner(petOwner), HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/pet-owners/{id}/subscriptions")
    public ResponseEntity<Subscription> createSubscription(@PathVariable Long id,@RequestBody Subscription subscription){
        // Obtener el PetOwner al que pertenece la mascota
        PetOwner petOwner = petOwnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet Owner not found with id: " + id));

        // Asignar el PetOwner a la mascota
        subscription.setPetOwner(petOwner);

        // Guardar el Pet (esto también guardará el Neck porque la relación es cascada)
        Subscription savedSubscription = subscriptionService.createSubscription(subscription);

        // Actualizar el PetOwner
        petOwner.getSubscriptions().add(savedSubscription);
        petOwnerRepository.save(petOwner);
        return new ResponseEntity<>(savedSubscription, HttpStatus.CREATED);
    }
    @GetMapping("/subscription/{id}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable Long id){
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Subscription not found with id: " + id));
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }
    @PutMapping("/subscription/{id}")
    public ResponseEntity<Object> updateSubscription(@PathVariable Long id, @RequestBody Subscription subscription){
        boolean isSubscriptionExist = subscriptionService.isSubscriptionExist(id);
        if(isSubscriptionExist){
            validateSubscription(subscription);
            subscription.setId(id);
            subscriptionService.updateSubscription(subscription);
            return new ResponseEntity<>("Updated succesfully", HttpStatus.OK);
        } else {
            throw new ValidationException("Data were not updated");
        }
    }
    @DeleteMapping("/subscription/{id}")
    public ResponseEntity<Object> deleteSubscription(@PathVariable Long id){
        if(subscriptionService.isSubscriptionExist(id)){
            subscriptionService.deleteSubscription(id);
            return new ResponseEntity<>("Deleted succesfully", HttpStatus.OK);
        } else {
            throw new ValidationException("Data were not deleted");
        }
    }
    private void validateSubscription(Subscription subscription){
        if(subscription.getStatus()== null || subscription.getStatus().trim().isEmpty()){
            throw new ValidationException("Subscription is required");
        }
        if(subscription.getPrice()== null || subscription.getPrice().trim().isEmpty()){
            throw new ValidationException("Subscription price is required");
        }
    }
}
