package com.xforce.pethealth.account_management.domain.model.commands;

public record CreatePetCommand(
        Long petOwnerId,
        String name,
        String specie,
        Integer age,
        String sex,
        String size,
        Double weight,
        Double perimeter,
        String image
) {
}
