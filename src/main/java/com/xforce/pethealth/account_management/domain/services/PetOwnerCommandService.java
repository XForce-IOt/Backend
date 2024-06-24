package com.xforce.pethealth.account_management.domain.services;

import com.xforce.pethealth.account_management.domain.model.commands.*;
import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import com.xforce.pethealth.account_management.domain.model.entities.Subscription;

import java.util.Optional;

public interface PetOwnerCommandService {
    Optional<PetOwner> handle(CreatePetOwnerCommand command);
    Optional<PetOwner> handle(UpdatePetOwnerCommand command);
    void handle(DeletePetOwnerCommand command);
    Optional<Pet> handle(AddPetCommand command);
    void handle(DeletePetCommand command);
    Optional<Pet> handle(UpdatePetCommand command);
    Optional<Subscription> handle(AddSubscriptionCommand command);
    Optional<Subscription> handle(UpdateSubscriptionCommand command);
}
