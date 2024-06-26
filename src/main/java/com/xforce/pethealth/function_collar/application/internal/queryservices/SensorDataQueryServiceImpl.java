package com.xforce.pethealth.function_collar.application.internal.queryservices;

import com.xforce.pethealth.function_collar.domain.model.entities.SensorData;
import com.xforce.pethealth.function_collar.domain.model.queries.GetAllSensorDataByPetIdQuery;
import com.xforce.pethealth.function_collar.domain.model.queries.GetAllSensorDataQuery;
import com.xforce.pethealth.function_collar.domain.model.queries.GetSensorDataByIdQuery;
import com.xforce.pethealth.function_collar.domain.services.SensorDataQueryService;
import com.xforce.pethealth.function_collar.infraestructure.persistence.jpa.repositories.SensorDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorDataQueryServiceImpl implements SensorDataQueryService {
    private final SensorDataRepository sensorDataRepository;

    public SensorDataQueryServiceImpl(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    @Override
    public List<SensorData> handle(GetAllSensorDataByPetIdQuery query) {
        return sensorDataRepository.findAllByPetIdAndPetPetOwnerId(query.petId(), query.petOwnerId());
    }

    @Override
    public List<SensorData> handle(GetAllSensorDataQuery query) {
        return sensorDataRepository.findAll();
    }

    @Override
    public Optional<SensorData> handle(GetSensorDataByIdQuery query) {
        return sensorDataRepository.findById(query.sensorDataId());
    }
}
