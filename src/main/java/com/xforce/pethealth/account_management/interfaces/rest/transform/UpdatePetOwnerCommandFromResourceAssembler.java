package com.xforce.pethealth.account_management.interfaces.rest.transform;

import com.xforce.pethealth.account_management.domain.model.commands.UpdatePetOwnerCommand;
import com.xforce.pethealth.account_management.interfaces.rest.resources.UpdatePetOwnerResource;

public class UpdatePetOwnerCommandFromResourceAssembler {
    public static UpdatePetOwnerCommand toCommandFromResource(Long petOwnerId, UpdatePetOwnerResource resource) {
        return new UpdatePetOwnerCommand(
                petOwnerId,
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
