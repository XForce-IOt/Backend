package com.xforce.pethealth.function_collar.application.internal.queryservices;

import com.xforce.pethealth.function_collar.domain.model.entities.SensorData;
import com.xforce.pethealth.function_collar.domain.model.queries.GetAllSensorDataByPetIdQuery;
import com.xforce.pethealth.function_collar.domain.model.queries.GetAllSensorDataQuery;
import com.xforce.pethealth.function_collar.domain.services.SensorDataQueryService;
import com.xforce.pethealth.function_collar.infraestructure.persistence.jpa.repositories.SensorDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorDataQueryServiceImpl implements SensorDataQueryService {
    private final SensorDataRepository sensorDataRepository;

    public SensorDataQueryServiceImpl(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    @Override
    public List<SensorData> handle(GetAllSensorDataByPetIdQuery query) {
        return sensorDataRepository.findAllByPetId(query.petId());
    }

    @Override
    public List<SensorData> handle(GetAllSensorDataQuery query) {
        return sensorDataRepository.findAll();
    }
}
