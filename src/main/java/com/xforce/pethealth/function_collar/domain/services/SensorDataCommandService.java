package com.xforce.pethealth.function_collar.domain.services;

import com.xforce.pethealth.function_collar.domain.model.commands.CreateSensorDataCommand;

public interface SensorDataCommandService {
    Long handle(CreateSensorDataCommand command);
}
