package com.xforce.pethealth.function_collar.domain.model.queries;

public record GetAllSensorDataByPetIdQuery(Long petId) {
    public GetAllSensorDataByPetIdQuery {
        if (petId == null || petId <= 0) {
            throw new IllegalArgumentException("Pet ID must be positive and not null");
        }
    }
}
