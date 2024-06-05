package com.xforce.pethealth.account_management.domain.model.queries;

public record GetAllPetsByNameQuery(Long petOwnerId, String name) {
    public GetAllPetsByNameQuery {
        if (petOwnerId == null || petOwnerId <= 0) {
            throw new IllegalArgumentException("PetOwnerId cannot be null");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }
}
