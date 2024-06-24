package com.xforce.pethealth.account_management.domain.model.commands;

public record DeletePetOwnerCommand(Long petOwnerId) {
    public DeletePetOwnerCommand {
        if (petOwnerId == null) {
            throw new IllegalArgumentException("Pet owner id cannot be null");
        }
    }
}
