package com.xforce.pethealth.appointment_function.domain.model.commands;

public record CreateVeterinarianCommand(
        Long clinicId,
        String firstName,
        String lastName,
        String password,
        String specialization,
        String phone,
        String email) {
}
