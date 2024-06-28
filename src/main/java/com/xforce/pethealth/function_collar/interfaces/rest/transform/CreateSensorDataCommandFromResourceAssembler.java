package com.xforce.pethealth.function_collar.interfaces.rest.transform;

import com.xforce.pethealth.function_collar.domain.model.commands.CreateSensorDataCommand;
import com.xforce.pethealth.function_collar.interfaces.rest.resources.CreateSensorDataResource;

public class CreateSensorDataCommandFromResourceAssembler {
    public static CreateSensorDataCommand toCommandFromResource(Long petOwnerId, Long petId) {
        return new CreateSensorDataCommand(petOwnerId, petId);
    }
}
