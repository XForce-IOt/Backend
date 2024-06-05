package com.xforce.pethealth.account_management.interfaces.rest.resources;

public record PetOwnerResource(
    Long id,
    String name,
    String lastName,
    String address,
    String phone,
    String email,
    String password,
    String image
) {
}
