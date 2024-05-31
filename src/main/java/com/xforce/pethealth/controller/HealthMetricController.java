package com.xforce.pethealth.controller;

import com.xforce.pethealth.entity.HealthMetric;
import com.xforce.pethealth.entity.Neck;
import com.xforce.pethealth.exception.ResourceNotFoundException;
import com.xforce.pethealth.repository.HealthMetricRepository;
import com.xforce.pethealth.repository.NeckRepository;
import com.xforce.pethealth.service.HealthMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pethealth/v1")
public class HealthMetricController {
    @Autowired
    HealthMetricService healthMetricService;
    @Autowired
    HealthMetricRepository healthMetricRepository;
    @Autowired
    NeckRepository neckRepository;

    @Transactional
    @PostMapping("/necks/{id}/healthmetrics")
    public ResponseEntity<HealthMetric> createHealthMetric(@PathVariable Long id, @RequestBody HealthMetric healthMetric){
        Neck neck = neckRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Neck not found with id: " + id));
        if (healthMetricService.isHealthMetricExist(id)) { //Si la metrica de salud esta asociada con un collar
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        healthMetric.setNeck(neck);
        healthMetric.setTimestamp(LocalDateTime.now()); //establece el tiempo actual
        return new ResponseEntity<>(healthMetricService.createHealthMetric(healthMetric), HttpStatus.CREATED);
    }

    @GetMapping("/necks/{id}/healthmetrics")
    public ResponseEntity<List<HealthMetric>> getHealthMetric(@PathVariable Long id){
        if (!neckRepository.existsById(id)) {
            throw new ResourceNotFoundException("Neck not found with id: " + id);
        }
        List<HealthMetric> healthMetric = healthMetricRepository.findByNeckId(id);
        if (healthMetric == null) {
            throw new ResourceNotFoundException("HealthMetric not found for neck with id: " + id);
        }
        return new ResponseEntity<>(healthMetric, HttpStatus.OK);
    }

    @PutMapping("/healthmetrics/{id}")
    public ResponseEntity<HealthMetric> updateHealthMetric(@PathVariable Long id, @RequestBody HealthMetric healthMetric){
        HealthMetric healthMetricToUpdate = healthMetricRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("HealthMetric not found with id: " + id));
        healthMetricToUpdate.setHeartRate(healthMetric.getHeartRate());
        healthMetricToUpdate.setPhysicalActivity(healthMetric.getPhysicalActivity());
        healthMetricToUpdate.setTemperature(healthMetric.getTemperature());
        healthMetricToUpdate.setSleepQuality(healthMetric.getSleepQuality());
        healthMetricToUpdate.setHydrationLevel(healthMetric.getHydrationLevel());
        healthMetricToUpdate.setLatitude(healthMetric.getLatitude());
        healthMetricToUpdate.setLongitude(healthMetric.getLongitude());
        healthMetricToUpdate.setTimestamp(LocalDateTime.now());
        healthMetricService.updateHealthMetric(healthMetricToUpdate);
        return new ResponseEntity<>(healthMetricToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/healthmetrics/{id}")
    public ResponseEntity<Object> deleteHealthMetric(@PathVariable("id") Long id){
        if (!healthMetricService.isHealthMetricExist(id)) {
            throw new ResourceNotFoundException("HealthMetric not found with id: " + id);
        }
        healthMetricService.deleteHealthMetric(id);
        return new ResponseEntity<>("Deleted succesfully", HttpStatus.OK);
    }

}
