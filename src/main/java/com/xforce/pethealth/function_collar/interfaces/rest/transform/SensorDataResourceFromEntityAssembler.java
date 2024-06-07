package com.xforce.pethealth.function_collar.interfaces.rest.transform;

import com.xforce.pethealth.function_collar.domain.model.entities.SensorData;
import com.xforce.pethealth.function_collar.interfaces.rest.resources.SensorDataResource;

public class SensorDataResourceFromEntityAssembler {
    public static SensorDataResource toResourceFromEntity(SensorData entity){
        return new SensorDataResource(entity.getId(), entity.getTemperature(), entity.getHumidity(), entity.getDistance(), entity.getPulse());
    }
}
