package com.xforce.pethealth.account_management.domain.model.commands;

public record DeletePetCommand(Long petOwnerId, Long petId) {
    public DeletePetCommand {
        if (petOwnerId == null) {
            throw new IllegalArgumentException("Pet owner id cannot be null");
        }
        if (petId == null) {
            throw new IllegalArgumentException("Pet id cannot be null");
        }
    }
}
