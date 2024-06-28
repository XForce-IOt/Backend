package com.xforce.pethealth.function_collar.interfaces;

import com.xforce.pethealth.function_collar.domain.model.commands.CreateSensorDataCommand;
import com.xforce.pethealth.function_collar.domain.model.entities.SensorData;
import com.xforce.pethealth.function_collar.domain.model.queries.GetAllSensorDataByPetIdQuery;
import com.xforce.pethealth.function_collar.domain.model.queries.GetAllSensorDataQuery;
import com.xforce.pethealth.function_collar.domain.model.queries.GetSensorDataByIdQuery;
import com.xforce.pethealth.function_collar.domain.services.SensorDataCommandService;
import com.xforce.pethealth.function_collar.domain.services.SensorDataQueryService;
import com.xforce.pethealth.function_collar.interfaces.rest.resources.SensorDataResource;
import com.xforce.pethealth.function_collar.interfaces.rest.transform.CreateSensorDataCommandFromResourceAssembler;
import com.xforce.pethealth.function_collar.interfaces.rest.transform.SensorDataResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins = "http://localhost:4200, https://backend-production-6ed3.up.railway.app, https://pet-health.netlify.app")
@CrossOrigin(origins = "https://pet-health.netlify.app")
@RequestMapping(value = "/api/pet-health/v1/pet-owners/{petOwnerId}/pets/{petId}/sensors_data", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sensor Data", description = "Sensor Data Management Endpoints")
public class SensorDataController {
    private final SensorDataCommandService sensorDataCommandService;
    private final SensorDataQueryService sensorDataQueryService;

    public SensorDataController(SensorDataCommandService sensorDataCommandService ,SensorDataQueryService sensorDataQueryService) {
        this.sensorDataCommandService = sensorDataCommandService;
        this.sensorDataQueryService = sensorDataQueryService;
    }

    @PostMapping
    public ResponseEntity<SensorDataResource> createSensorData(@PathVariable("petOwnerId") Long petOwnerId, @PathVariable("petId") Long petId) {
        CreateSensorDataCommand command = CreateSensorDataCommandFromResourceAssembler.toCommandFromResource(petOwnerId, petId);
        Long sensorDataId = sensorDataCommandService.handle(command);

        if (sensorDataId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getSensorDataByIdQuery = new GetSensorDataByIdQuery(sensorDataId);
        var sensorData = sensorDataQueryService.handle(getSensorDataByIdQuery);
        if (sensorData.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var sensorDataResource = SensorDataResourceFromEntityAssembler.toResourceFromEntity(sensorData.get());
        return new ResponseEntity<>(sensorDataResource, HttpStatus.CREATED);
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
