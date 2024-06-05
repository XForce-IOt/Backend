package com.xforce.pethealth.account_management.domain.model.queries;

public record GetPetByIdQuery(Long petOwnerId, Long petId) {
    public GetPetByIdQuery {
        if (petOwnerId == null || petOwnerId <= 0) {
            throw new IllegalArgumentException("Pet owner ID cannot be null");
        }
        if (petId == null || petId <= 0) {
            throw new IllegalArgumentException("Pet id must not be null");
        }
    }
}
