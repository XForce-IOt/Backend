package com.xforce.pethealth.function_collar.domain.model.queries;

public record GetAllSensorDataByPetIdQuery(Long petOwnerId, Long petId) {
    public GetAllSensorDataByPetIdQuery {
        if (petOwnerId == null || petOwnerId <= 0) {
            throw new IllegalArgumentException("Pet owner ID must be positive and not null");
        }
        if (petId == null || petId <= 0) {
            throw new IllegalArgumentException("Pet ID must be positive and not null");
        }
    }
}
