package com.xforce.pethealth.function_collar.domain.services;

import com.xforce.pethealth.function_collar.domain.model.entities.SensorData;
import com.xforce.pethealth.function_collar.domain.model.queries.GetAllSensorDataByPetIdQuery;
import com.xforce.pethealth.function_collar.domain.model.queries.GetAllSensorDataQuery;
import com.xforce.pethealth.function_collar.domain.model.queries.GetSensorDataByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SensorDataQueryService {
    List<SensorData> handle(GetAllSensorDataQuery query);
    List<SensorData> handle(GetAllSensorDataByPetIdQuery query);
    Optional<SensorData> handle(GetSensorDataByIdQuery query);
}
