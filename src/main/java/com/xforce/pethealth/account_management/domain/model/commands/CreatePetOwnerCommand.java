package com.xforce.pethealth.account_management.domain.model.commands;


public record CreatePetOwnerCommand(String name, String lastName, String address, String phone, String email, String password, String image) {
    public CreatePetOwnerCommand {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("name cannot be null or empty");
        if (lastName == null || lastName.isEmpty())
            throw new IllegalArgumentException("lastName cannot be null or empty");
        if (address == null || address.isEmpty())
            throw new IllegalArgumentException("address cannot be null or empty");
        if (phone == null || phone.isEmpty())
            throw new IllegalArgumentException("phone cannot be null or empty");
        if (email == null || email.isEmpty())
            throw new IllegalArgumentException("email cannot be null or empty");
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("password cannot be null or empty");
        if (image == null || image.isEmpty())
            throw new IllegalArgumentException("image cannot be null or empty");
    }
}
