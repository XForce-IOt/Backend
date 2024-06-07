package com.xforce.pethealth.function_collar.domain.model.commands;

public record CreateSensorDataCommand(
        Long petOwnerId,
        Long petId,
        double temperature,
        double humidity,
        double distance,
        double pulse) {
}
