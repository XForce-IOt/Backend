package com.xforce.pethealth.function_collar.interfaces.rest.resources;

public record SensorDataResource(
        Long id,
        double temperature,
        double humidity,
        double distance,
        double pulse
) {
}
