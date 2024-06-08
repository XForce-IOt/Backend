package com.xforce.pethealth.account_management.interfaces.rest.resources;

public record PetResource(Long id, String name, String specie, Integer age, String sex, String size, Double perimeter, String image) {
}
