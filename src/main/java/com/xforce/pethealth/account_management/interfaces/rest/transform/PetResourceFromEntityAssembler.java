package com.xforce.pethealth.account_management.interfaces.rest.transform;

import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import com.xforce.pethealth.account_management.interfaces.rest.resources.PetResource;

public class PetResourceFromEntityAssembler {
    public static PetResource toResourceFromEntity(Pet pet) {
        return new PetResource(
            pet.getId(),
                pet.getName(),
                pet.getSpecie(),
                pet.getAge(),
                pet.getSex(),
                pet.getSize(),
                //pet.getWeight(),
                pet.getPerimeter(),
                pet.getImage()
        );
    }
}
