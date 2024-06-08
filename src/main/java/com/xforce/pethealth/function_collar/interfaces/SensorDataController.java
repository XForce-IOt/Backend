package com.xforce.pethealth.function_collar.interfaces;

import com.xforce.pethealth.function_collar.domain.model.entities.SensorData;
import com.xforce.pethealth.function_collar.domain.model.queries.GetAllSensorDataByPetIdQuery;
import com.xforce.pethealth.function_collar.domain.model.queries.GetAllSensorDataQuery;
import com.xforce.pethealth.function_collar.domain.services.SensorDataQueryService;
import com.xforce.pethealth.function_collar.interfaces.rest.resources.SensorDataResource;
import com.xforce.pethealth.function_collar.interfaces.rest.transform.SensorDataResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/pet-health/v1/pet-owners/{petOwnerId}/pets/{petId}/sensors_data", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sensor Data", description = "Sensor Data Management Endpoints")
public class SensorDataController {
    private final SensorDataQueryService sensorDataQueryService;

    public SensorDataController(SensorDataQueryService sensorDataQueryService) {
        this.sensorDataQueryService = sensorDataQueryService;
    }

    @GetMapping
    public ResponseEntity<List<SensorDataResource>> getAllSensorDataByPetOwnerAndPet(@PathVariable("petOwnerId") Long petOwnerId, @PathVariable("petId") Long petId) {
        GetAllSensorDataByPetIdQuery query = new GetAllSensorDataByPetIdQuery(petOwnerId, petId);
        List<SensorData> sensorDataList = sensorDataQueryService.handle(query);
        List<SensorDataResource> resources = sensorDataList.stream()
                .map(SensorDataResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    // Obtener todos los SensorData en el sistema
    @GetMapping("/all")
    public ResponseEntity<List<SensorDataResource>> getAllSensorData() {
        var sensorData = sensorDataQueryService.handle(new GetAllSensorDataQuery());
        var resources = sensorData.stream()
                .map(SensorDataResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
}
