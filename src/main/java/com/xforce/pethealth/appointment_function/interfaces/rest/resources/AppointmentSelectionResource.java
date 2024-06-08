package com.xforce.pethealth.appointment_function.interfaces.rest.resources;

public record AppointmentSelectionResource(
        Long id,
        Long petOwnerId,
        Long appointmentId
) {
}
