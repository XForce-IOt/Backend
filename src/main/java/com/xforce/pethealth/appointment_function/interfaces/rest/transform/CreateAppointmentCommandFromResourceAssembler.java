package com.xforce.pethealth.appointment_function.interfaces.rest.transform;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateAppointmentCommand;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource) {
        return new CreateAppointmentCommand(resource.veterinarianId(), resource.dateTime(), resource.description(), resource.initialStatus());
    }
}
