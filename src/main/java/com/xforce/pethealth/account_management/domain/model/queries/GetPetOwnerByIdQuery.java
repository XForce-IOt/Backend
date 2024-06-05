package com.xforce.pethealth.account_management.domain.model.queries;

public record GetPetOwnerByIdQuery(Long id) {
    public GetPetOwnerByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
