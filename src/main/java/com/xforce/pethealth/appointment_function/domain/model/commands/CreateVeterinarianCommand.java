package com.xforce.pethealth.appointment_function.domain.model.commands;

public record CreateVeterinarianCommand(
        String firstName,
        String lastName,
        String specialization,
        String phone,
        String email) {
}
