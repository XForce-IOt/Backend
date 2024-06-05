package com.xforce.pethealth.account_management.interfaces.rest.transform;

import com.xforce.pethealth.account_management.domain.model.commands.CreatePetOwnerCommand;
import com.xforce.pethealth.account_management.interfaces.rest.resources.CreatePetOwnerResource;

public class CreatePetOwnerCommandFromResourceAssembler {
    public static CreatePetOwnerCommand toCommandFromResource(CreatePetOwnerResource resource) {
        return new CreatePetOwnerCommand(
                resource.name(),
                resource.lastName(),
                resource.address(),
                resource.phone(),
                resource.email(),
                resource.password(),
                resource.image()
        );
    }
}
