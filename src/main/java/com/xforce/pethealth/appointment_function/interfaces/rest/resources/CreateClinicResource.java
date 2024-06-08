package com.xforce.pethealth.appointment_function.interfaces.rest.resources;

public record CreateClinicResource(
        double rating,
        String number,
        String socialMedia
) {
}
