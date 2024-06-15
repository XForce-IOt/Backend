package com.xforce.pethealth.appointment_function.interfaces.rest.transform;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateAppointmentSelectionCommand;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.CreateAppointmentSelectionResource;

public class CreateAppointmentSelectionCommandFromResourceAssembler {
    public static CreateAppointmentSelectionCommand toCommandFromResource(Long petOwnerId, Long petId, CreateAppointmentSelectionResource resource) {
        return new CreateAppointmentSelectionCommand(
                petOwnerId,
                petId,
                resource.appointmentId()
        );
    }
}
