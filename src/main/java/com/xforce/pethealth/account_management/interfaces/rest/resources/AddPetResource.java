package com.xforce.pethealth.account_management.interfaces.rest.resources;

public record AddPetResource(Long petOwnerId, String name, String specie, Integer age, String sex, String size, Double weight, Double perimeter, String image) {
    public AddPetResource {
        if (petOwnerId == null)
            throw new IllegalArgumentException("petOwnerId cannot be null");
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("name cannot be null or empty");
        if (specie == null || specie.isEmpty())
            throw new IllegalArgumentException("specie cannot be null or empty");
        if (age == null)
            throw new IllegalArgumentException("age cannot be null");
        if (sex == null || sex.isEmpty())
            throw new IllegalArgumentException("sex cannot be null or empty");
        if (size == null || size.isEmpty())
            throw new IllegalArgumentException("size cannot be null or empty");
        if (weight == null)
            throw new IllegalArgumentException("weight cannot be null");
        if (perimeter == null)
            throw new IllegalArgumentException("perimeter cannot be null");
        if (image == null || image.isEmpty())
            throw new IllegalArgumentException("image cannot be null or empty");
    }
}
