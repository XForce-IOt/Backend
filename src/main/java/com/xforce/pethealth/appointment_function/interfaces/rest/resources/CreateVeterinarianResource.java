package com.xforce.pethealth.appointment_function.interfaces.rest.resources;

public record CreateVeterinarianResource(
        String firstName,
        String lastName,
        String password,
        String specialization,
        String phone,
        String email
) {
}
