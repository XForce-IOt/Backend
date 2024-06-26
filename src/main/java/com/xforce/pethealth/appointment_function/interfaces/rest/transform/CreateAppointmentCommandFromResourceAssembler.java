package com.xforce.pethealth.appointment_function.interfaces.rest.transform;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateAppointmentCommand;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(Long clinicId, Long vetId, Long petOwnerId, CreateAppointmentResource resource) {
        return new CreateAppointmentCommand(clinicId, vetId, petOwnerId, resource.petId(), resource.title(), resource.dateTime(), resource.description(), resource.initialStatus());
    }
}
