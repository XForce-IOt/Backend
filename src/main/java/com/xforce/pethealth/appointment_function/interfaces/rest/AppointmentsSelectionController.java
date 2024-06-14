package com.xforce.pethealth.appointment_function.interfaces.rest;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateAppointmentSelectionCommand;
import com.xforce.pethealth.appointment_function.domain.model.commands.DeleteAppointmentSelectionCommand;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllAppointmentSelectionsByPetOwnerIdQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAppointmentSelectionByIdQuery;
import com.xforce.pethealth.appointment_function.domain.services.AppointmentSelectionCommandService;
import com.xforce.pethealth.appointment_function.domain.services.AppointmentSelectionQueryService;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.AppointmentSelectionResource;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.CreateAppointmentSelectionResource;
import com.xforce.pethealth.appointment_function.interfaces.rest.transform.AppointmentSelectionResourceFromEntityAssembler;
import com.xforce.pethealth.appointment_function.interfaces.rest.transform.CreateAppointmentSelectionCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/pet-health/v1/pet-owners/{petOwnerId}/appointment-selections", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Appointments Selection", description = "Appointment Selection Management Endpoints")
public class AppointmentsSelectionController {
    private final AppointmentSelectionCommandService commandService;
    private final AppointmentSelectionQueryService queryService;

    public AppointmentsSelectionController(AppointmentSelectionCommandService commandService, AppointmentSelectionQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<AppointmentSelectionResource> createAppointmentSelection(@PathVariable Long petOwnerId, @RequestBody CreateAppointmentSelectionResource resource) {
        CreateAppointmentSelectionCommand command = CreateAppointmentSelectionCommandFromResourceAssembler.toCommandFromResource(petOwnerId, resource);
        Long selectionId = commandService.handle(command);
        if (selectionId == null) {
            return ResponseEntity.badRequest().build();
        }
        var selection = queryService.handle(new GetAppointmentSelectionByIdQuery(petOwnerId, selectionId)).orElse(null);
        if (selection == null) {
            return ResponseEntity.notFound().build();
        }
        var selectionResource = AppointmentSelectionResourceFromEntityAssembler.toResourceFromEntity(selection);
        return new ResponseEntity<>(selectionResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentSelectionResource>> getAllAppointmentSelections(@PathVariable Long petOwnerId) {
        var query = new GetAllAppointmentSelectionsByPetOwnerIdQuery(petOwnerId);
        List<AppointmentSelectionResource> resources = queryService.handle(query)
                .stream()
                .map(AppointmentSelectionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{selectionId}")
    public ResponseEntity<Void> deleteAppointmentSelection(@PathVariable("petOwnerId") Long petOwnerId, @PathVariable("selectionId") Long selectionId) {
        try {
            commandService.handle(new DeleteAppointmentSelectionCommand(petOwnerId, selectionId));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
