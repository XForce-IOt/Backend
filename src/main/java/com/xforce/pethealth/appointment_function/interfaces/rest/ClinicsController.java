package com.xforce.pethealth.appointment_function.interfaces.rest;

import com.xforce.pethealth.appointment_function.domain.model.commands.DeleteClinicCommand;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllClinicsQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetClinicByIdQuery;
import com.xforce.pethealth.appointment_function.domain.services.ClinicCommandService;
import com.xforce.pethealth.appointment_function.domain.services.ClinicQueryService;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.ClinicResource;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.CreateClinicResource;
import com.xforce.pethealth.appointment_function.interfaces.rest.transform.ClinicResourceFromEntityAssembler;
import com.xforce.pethealth.appointment_function.interfaces.rest.transform.CreateClinicCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200, https://backend-production-6ed3.up.railway.app, https://pet-health.netlify.app")
@CrossOrigin(origins = "https://pet-health.netlify.app")
@RequestMapping(value = "/api/pet-health/v1/clinics", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Clinics", description = "Clinics Management Endpoints")
public class ClinicsController {
    private final ClinicCommandService clinicCommandService;
    private final ClinicQueryService clinicQueryService;

    public ClinicsController(ClinicCommandService clinicCommandService, ClinicQueryService clinicQueryService) {
        this.clinicCommandService = clinicCommandService;
        this.clinicQueryService = clinicQueryService;
    }

    @PostMapping
    public ResponseEntity<ClinicResource> createClinic(@RequestBody CreateClinicResource createClinicResource) {
        var createClinicCommand = CreateClinicCommandFromResourceAssembler.toCommandFromResource(createClinicResource);
        var clinicId = clinicCommandService.handle(createClinicCommand);
        if (clinicId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getClinicByIdQuery = new GetClinicByIdQuery(clinicId);
        var clinic = clinicQueryService.handle(getClinicByIdQuery);

        if (clinic.isEmpty()) return ResponseEntity.badRequest().build();

        var clinicResource = ClinicResourceFromEntityAssembler.toResourceFromEntity(clinic.get());
        return new ResponseEntity<>(clinicResource, HttpStatus.CREATED);
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<ClinicResource> getClinicById(@PathVariable Long clinicId) {
        var getClinicByIdQuery = new GetClinicByIdQuery(clinicId);
        var clinic = clinicQueryService.handle(getClinicByIdQuery);
        if (clinic.isEmpty()) return ResponseEntity.badRequest().build();
        var clinicResource = ClinicResourceFromEntityAssembler.toResourceFromEntity(clinic.get());
        return ResponseEntity.ok(clinicResource);
    }

    @GetMapping
    public ResponseEntity<List<ClinicResource>> getAllClinic() {
        var getAllClinicQuery = new GetAllClinicsQuery();
        var clinics = clinicQueryService.handle(getAllClinicQuery);
        var clinicResources = clinics.stream().map(ClinicResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(clinicResources);
    }

    @DeleteMapping("/{clinicId}")
    public ResponseEntity<?> deleteClinic(@PathVariable Long clinicId) {
        var deleteClinicCommand = new DeleteClinicCommand(clinicId);
        clinicCommandService.handle(deleteClinicCommand);
        return ResponseEntity.ok("Clinic with given id has been deleted");
    }
}
