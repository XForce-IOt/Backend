package com.xforce.pethealth.appointment_function.interfaces.rest.transform;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateClinicCommand;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.CreateClinicResource;

public class CreateClinicCommandFromResourceAssembler {
    public static CreateClinicCommand toCommandFromResource(CreateClinicResource resource) {
        return new CreateClinicCommand(resource.rating(), resource.name(), resource.number(), resource.socialMedia(), resource.profilePicture());
    }
}
