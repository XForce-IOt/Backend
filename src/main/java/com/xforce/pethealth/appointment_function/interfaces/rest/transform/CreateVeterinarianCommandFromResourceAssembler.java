package com.xforce.pethealth.appointment_function.interfaces.rest.transform;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateVeterinarianCommand;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.CreateVeterinarianResource;

public class CreateVeterinarianCommandFromResourceAssembler {
    public static CreateVeterinarianCommand toCommandFromResource(Long clinicId, CreateVeterinarianResource resource) {
        return new CreateVeterinarianCommand(clinicId, resource.firstName(), resource.lastName(), resource.password(), resource.specialization(), resource.phone(), resource.email());
    }
}
