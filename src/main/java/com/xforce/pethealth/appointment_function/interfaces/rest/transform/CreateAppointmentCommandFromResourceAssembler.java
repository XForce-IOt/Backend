package com.xforce.pethealth.appointment_function.interfaces.rest.transform;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateAppointmentCommand;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(Long vetId, CreateAppointmentResource resource) {
        return new CreateAppointmentCommand(vetId, resource.dateTime(), resource.description(), resource.initialStatus());
    }
}