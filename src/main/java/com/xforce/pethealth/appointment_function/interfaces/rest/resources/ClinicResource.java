package com.xforce.pethealth.appointment_function.interfaces.rest.resources;

public record ClinicResource(
        Long clinicId,
        double rating,
        String name,
        String number,
        String socialMedia,
        String profilePicture
) {
}
