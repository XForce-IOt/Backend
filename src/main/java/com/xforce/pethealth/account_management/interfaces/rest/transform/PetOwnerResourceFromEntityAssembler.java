package com.xforce.pethealth.account_management.interfaces.rest.transform;

import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.interfaces.rest.resources.PetOwnerResource;

public class PetOwnerResourceFromEntityAssembler {
    public static PetOwnerResource toResourceFromEntity(PetOwner entity) {
        return new PetOwnerResource(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getAddress(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getImage());
    }
}
