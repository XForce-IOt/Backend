package com.xforce.pethealth.account_management.interfaces.rest;

import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.domain.model.commands.DeletePetCommand;
import com.xforce.pethealth.account_management.domain.model.commands.DeletePetOwnerCommand;
import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import com.xforce.pethealth.account_management.domain.model.entities.Subscription;
import com.xforce.pethealth.account_management.domain.model.queries.*;
import com.xforce.pethealth.account_management.domain.services.PetOwnerCommandService;
import com.xforce.pethealth.account_management.domain.services.PetOwnerQueryService;
import com.xforce.pethealth.account_management.interfaces.rest.resources.*;
import com.xforce.pethealth.account_management.interfaces.rest.transform.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
//@CrossOrigin(origins = "http://localhost:4200, https://backend-production-6ed3.up.railway.app, https://pet-health.netlify.app")
@CrossOrigin(origins = "https://pet-health.netlify.app") 
@RequestMapping("/api/pet-health/v1/pet-owners")
public class PetOwnerController {
    private final PetOwnerCommandService petOwnerCommandService;
    private final PetOwnerQueryService petOwnerQueryService;

    public PetOwnerController(PetOwnerCommandService petOwnerCommandService, PetOwnerQueryService petOwnerQueryService) {
        this.petOwnerCommandService = petOwnerCommandService;
        this.petOwnerQueryService = petOwnerQueryService;
    }

    @PostMapping("")
    public ResponseEntity<PetOwnerResource> createPetOwner(@RequestBody CreatePetOwnerResource resource) {
        Optional<PetOwner> petOwner = petOwnerCommandService.handle(
                CreatePetOwnerCommandFromResourceAssembler.toCommandFromResource(resource));
        return petOwner.map(source -> new ResponseEntity<>(
                PetOwnerResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("")
    public ResponseEntity<List<PetOwnerResource>> getAllPetOwners(){
        List<PetOwner> petOwners = petOwnerQueryService.handle(new GetAllPetOwnersQuery());
        List<PetOwnerResource> petOwnerResources = petOwners.stream()
                .map(PetOwnerResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(petOwnerResources);
    }
    @GetMapping("/{petOwnerId}")
    public ResponseEntity<PetOwnerResource> getPetOwner(@PathVariable Long petOwnerId) {
        Optional<PetOwner> petOwner = petOwnerQueryService.handle(new GetPetOwnerByIdQuery(petOwnerId));
        return petOwner.map(source -> ResponseEntity.ok(PetOwnerResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{petOwnerId}")
    public ResponseEntity<PetOwnerResource> updatePetOwner(@PathVariable Long petOwnerId, @RequestBody UpdatePetOwnerResource resource) {
        var command = UpdatePetOwnerCommandFromResourceAssembler.toCommandFromResource(petOwnerId, resource);
        Optional<PetOwner> updatePetOwner = petOwnerCommandService.handle(command);
        return updatePetOwner.map(value -> ResponseEntity.ok(PetOwnerResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{petOwnerId}")
    public ResponseEntity<Void> deletePetOwner(@PathVariable Long petOwnerId) {
        petOwnerCommandService.handle(new DeletePetOwnerCommand(petOwnerId));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{petOwnerId}/pets")
    public ResponseEntity<PetResource> createPet(@PathVariable Long petOwnerId, @RequestBody AddPetResource resource) {
        var command = AddPetCommandFromResourceAssembler.toCommandFromResource(petOwnerId, resource);
        Optional<Pet> pet = petOwnerCommandService.handle(command);
        return pet.map(value -> ResponseEntity.ok(PetResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/{petOwnerId}/pets/{petId}")
    public ResponseEntity<PetResource> updatePet(@PathVariable Long petOwnerId, @PathVariable Long petId, @RequestBody UpdatePetResource resource) {
        var command = UpdatePetCommandFromResourceAssembler.toCommandFromResource(petOwnerId, petId, resource);
        Optional<Pet> pet = petOwnerCommandService.handle(command);
        return pet.map(value -> ResponseEntity.ok(PetResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/{petOwnerId}/pets")
    public ResponseEntity<List<PetResource>> getAllPets(@PathVariable Long petOwnerId){
        List<Pet> pets = petOwnerQueryService.handle(new GetAllPetsQuery(petOwnerId));
        List<PetResource> petResources = pets.stream()
                .map(PetResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(petResources);
    }
    @GetMapping("/{petOwnerId}/pets/filter")
    public ResponseEntity<List<PetResource>> getAllPetsByName(@PathVariable Long petOwnerId,@RequestParam String name){
        List<Pet> pets = petOwnerQueryService.handle(new GetAllPetsByNameQuery(petOwnerId, name));
        List<PetResource> petResources = pets.stream()
                .map(PetResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(petResources);
    }
    @GetMapping("/{petOwnerId}/pets/{petId}")
    public ResponseEntity<PetResource> getPet(@PathVariable Long petOwnerId, @PathVariable Long petId) {
        Pet pet = petOwnerQueryService.handle(new GetPetByIdQuery(petOwnerId, petId));
        PetResource petResource = PetResourceFromEntityAssembler.toResourceFromEntity(pet);
        return ResponseEntity.ok(petResource);
    }
    @DeleteMapping("/{petOwnerId}/pets/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable Long petOwnerId, @PathVariable Long petId) {
        DeletePetCommand command = new DeletePetCommand(petOwnerId, petId);
        petOwnerCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{petOwnerId}/subscriptions")
    public ResponseEntity<SubscriptionResource> createSubscription(@PathVariable Long petOwnerId, @RequestBody AddSubscriptionResource resource) {
        var command = AddSubscriptionCommandFromResourceAssembler.toCommandFromResource(petOwnerId, resource);
        Optional<Subscription> subscription = petOwnerCommandService.handle(command);
        return subscription.map(value -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/{petOwnerId}/subscriptions/{subscriptionId}")
    public ResponseEntity<SubscriptionResource> updateSubscription(@PathVariable Long petOwnerId, @PathVariable Long subscriptionId, @RequestBody UpdateSubscriptionResource resource) {
        var command = UpdateSubscriptionCommandFromResourceAssembler.toCommandFromResource(petOwnerId, subscriptionId, resource);
        Optional<Subscription> subscription = petOwnerCommandService.handle(command);
        return subscription.map(value -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/{petOwnerId}/subscriptions/{subscriptionId}")
    public ResponseEntity<SubscriptionResource> getSubscription(@PathVariable Long petOwnerId, @PathVariable Long subscriptionId) {
        Subscription subscription = petOwnerQueryService.handle(new GetSubscriptionByIdQuery(petOwnerId, subscriptionId));
        SubscriptionResource subscriptionResource = SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription);
        return ResponseEntity.ok(subscriptionResource);
    }

}
