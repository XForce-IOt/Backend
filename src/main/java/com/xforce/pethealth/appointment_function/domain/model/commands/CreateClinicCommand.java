package com.xforce.pethealth.appointment_function.domain.model.commands;

public record CreateClinicCommand(
        double rating,
        String name,
        String number,
        String socialMedia
) {
}
