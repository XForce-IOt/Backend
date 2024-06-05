package com.xforce.pethealth.account_management.domain.model.commands;

public record UpdatePetOwnerCommand(Long id, String name, String lastName, String address, String phone, String email, String password, String image) {
    public UpdatePetOwnerCommand{
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address is required");
        }
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone is required");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (image == null || image.isBlank()) {
            throw new IllegalArgumentException("Image is required");
        }
    }
}
