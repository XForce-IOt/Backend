package com.xforce.pethealth.appointment_function.domain.model.queries;

public record GetAllVeterinarianByClinicIdQuery(Long clinicId) {
    public GetAllVeterinarianByClinicIdQuery {
        if (clinicId == null || clinicId <= 0) {
            throw new IllegalArgumentException("Clinic ID must be positive and not null");
        }
    }
}
