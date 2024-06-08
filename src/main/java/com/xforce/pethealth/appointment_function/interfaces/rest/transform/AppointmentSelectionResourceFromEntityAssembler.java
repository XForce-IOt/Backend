package com.xforce.pethealth.appointment_function.interfaces.rest.transform;

import com.xforce.pethealth.appointment_function.domain.model.entities.AppointmentSelection;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.AppointmentSelectionResource;

public class AppointmentSelectionResourceFromEntityAssembler {
    public static AppointmentSelectionResource toResourceFromEntity(AppointmentSelection entity) {
        return new AppointmentSelectionResource(
                entity.getId(),
                entity.getPetOwner().getId(),
                entity.getAppointment().getId()
        );
    }
}
