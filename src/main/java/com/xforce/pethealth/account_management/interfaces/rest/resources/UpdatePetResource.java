package com.xforce.pethealth.account_management.interfaces.rest.resources;

public record UpdatePetResource (Long petOwnerId, Long petId, String name, String specie, int age, String sex, String size, double perimeter, String image) {
    public UpdatePetResource {
        if (petOwnerId == null)
            throw new IllegalArgumentException("petOwnerId cannot be null");
        if (petId == null)
            throw new IllegalArgumentException("id cannot be null");
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("name cannot be null or empty");
        if (specie == null || specie.isEmpty())
            throw new IllegalArgumentException("specie cannot be null or empty");
        if (age < 0)
            throw new IllegalArgumentException("age cannot be negative");
        if (sex == null || sex.isEmpty())
            throw new IllegalArgumentException("sex cannot be null or empty");
        if (size == null || size.isEmpty())
            throw new IllegalArgumentException("size cannot be null or empty");
        /*if (weight < 0)
            throw new IllegalArgumentException("weight cannot be negative");*/
        if (perimeter < 0)
            throw new IllegalArgumentException("perimeter cannot be negative");
        if (image == null || image.isEmpty())
            throw new IllegalArgumentException("image cannot be null or empty");
    }
}
