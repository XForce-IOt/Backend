package com.xforce.pethealth.appointment_function.interfaces.rest.resources;

public record AppointmentResource(
        Long id,
        Long petId,
        String title,
        String dateTime,
        String description
) {
}
