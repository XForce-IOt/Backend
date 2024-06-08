package com.xforce.pethealth.account_management.interfaces.rest.transform;

import com.xforce.pethealth.account_management.domain.model.commands.AddPetCommand;
import com.xforce.pethealth.account_management.interfaces.rest.resources.AddPetResource;

public class AddPetCommandFromResourceAssembler {
    public static AddPetCommand toCommandFromResource(Long petOwnerId, AddPetResource resource) {
        return new AddPetCommand(
                petOwnerId,
                resource.name(),
                resource.specie(),
                resource.age(),
                resource.sex(),
                resource.size(),
                //resource.weight(),
                resource.perimeter(),
                resource.image()
        );
    }
}
