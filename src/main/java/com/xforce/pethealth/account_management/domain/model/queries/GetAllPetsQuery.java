package com.xforce.pethealth.account_management.domain.model.queries;

public record GetAllPetsQuery(Long petOwnerId) {
    public GetAllPetsQuery {
        if (petOwnerId == null || petOwnerId <= 0) {
            throw new IllegalArgumentException("PetOwnerId cannot be null");
        }
    }
}
