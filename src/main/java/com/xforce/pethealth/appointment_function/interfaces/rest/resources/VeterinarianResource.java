package com.xforce.pethealth.appointment_function.interfaces.rest.resources;

public record VeterinarianResource(
        Long veterinarianId,
        String fullName,
        String password,
        String specialization,
        String contactInfo
) {
}
