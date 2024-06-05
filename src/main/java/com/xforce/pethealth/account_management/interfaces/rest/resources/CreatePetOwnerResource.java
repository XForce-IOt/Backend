package com.xforce.pethealth.account_management.interfaces.rest.resources;

public record CreatePetOwnerResource(String name, String lastName, String address, String phone, String email, String password, String image) {
    public CreatePetOwnerResource {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or empty");
        if (lastName == null || lastName.isBlank()) throw new IllegalArgumentException("LastName cannot be null or empty");
        if (address == null || address.isBlank()) throw new IllegalArgumentException("Address cannot be null or empty");
        if (phone == null || phone.isBlank()) throw new IllegalArgumentException("Phone cannot be null or empty");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email cannot be null or empty");
        if (password == null || password.isBlank()) throw new IllegalArgumentException("Password cannot be null or empty");
        if (image == null || image.isBlank()) throw new IllegalArgumentException("Image cannot be null or empty");
    }
}
