package com.xforce.pethealth.function_collar.application.internal.commandservices;

import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import com.xforce.pethealth.function_collar.domain.model.commands.CreateSensorDataCommand;
import com.xforce.pethealth.function_collar.domain.model.entities.SensorData;
import com.xforce.pethealth.function_collar.domain.services.SensorDataCommandService;
import com.xforce.pethealth.function_collar.infraestructure.persistence.jpa.repositories.SensorDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SensorDataCommandServiceImpl implements SensorDataCommandService {

    @Autowired
    private RestTemplate restTemplate;

    private SensorDataRepository sensorDataRepository;
    private PetOwnerRepository petOwnerRepository;

    public SensorDataCommandServiceImpl(SensorDataRepository sensorDataRepository, PetOwnerRepository petOwnerRepository) {
        this.sensorDataRepository = sensorDataRepository;
        this.petOwnerRepository = petOwnerRepository;
    }

    @Transactional
    @Override
    public Long handle(CreateSensorDataCommand command) {
        // Verifica que el dueño y la mascota existen y están relacionados
        PetOwner petOwner = petOwnerRepository.findById(command.petOwnerId())
                .orElseThrow(() -> new RuntimeException("Pet owner not found with ID: " + command.petOwnerId()));

        Pet pet = petOwner.getPets().stream()
                .filter(p -> p.getId().equals(command.petId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No pet found with ID: " + command.petId() + " for this pet owner"));

        String url = "https://hearty-serenity-production.up.railway.app/api/v1/sensor_data";
        SensorData fetchedData = restTemplate.getForObject(url, SensorData.class);

        if (fetchedData == null) {
            throw new RuntimeException("No sensor data could be fetched.");
        }

        SensorData newSensorData = new SensorData(
                petOwner,
                pet,
                fetchedData.getTemperature(),
                fetchedData.getHumidity(),
                fetchedData.getDistance(),
                fetchedData.getPulse()
        );

        sensorDataRepository.save(newSensorData);
        return newSensorData.getId();
    }


}
