package com.xforce.pethealth.account_management.domain.services;

import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import com.xforce.pethealth.account_management.domain.model.entities.Subscription;
import com.xforce.pethealth.account_management.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface PetOwnerQueryService {
    List<PetOwner> handle(GetAllPetOwnersQuery query);
    Optional<PetOwner> handle(GetPetOwnerByIdQuery query);
    List<Pet> handle(GetAllPetsQuery query);
    Pet handle(GetPetByIdQuery query);
    List<Pet> handle(GetAllPetsByNameQuery query);
    Subscription handle(GetSubscriptionByIdQuery query);
}
