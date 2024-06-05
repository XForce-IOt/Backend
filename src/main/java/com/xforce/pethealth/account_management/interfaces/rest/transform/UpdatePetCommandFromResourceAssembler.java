package com.xforce.pethealth.account_management.interfaces.rest.transform;

import com.xforce.pethealth.account_management.domain.model.commands.UpdatePetCommand;
import com.xforce.pethealth.account_management.interfaces.rest.resources.UpdatePetResource;

public class UpdatePetCommandFromResourceAssembler {
    public static UpdatePetCommand toCommandFromResource(Long petOwnerId, Long petId, UpdatePetResource resource) {
        return new UpdatePetCommand(
                petOwnerId,
                petId,
                resource.name(),
                resource.specie(),
                resource.age(),
                resource.sex(),
                resource.size(),
                resource.weight(),
                resource.perimeter(),
                resource.image()
        );
    }
}
