package com.xforce.pethealth.appointment_function.interfaces.rest;

import com.xforce.pethealth.appointment_function.domain.model.aggregates.Appointment;
import com.xforce.pethealth.appointment_function.domain.model.commands.DeleteAppointmentCommand;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllAppointmentsByVeterinarianIdQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllAppointmentsQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAppointmentByIdQuery;
import com.xforce.pethealth.appointment_function.domain.services.AppointmentCommandService;
import com.xforce.pethealth.appointment_function.domain.services.AppointmentQueryService;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.AppointmentResource;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.CreateAppointmentResource;
import com.xforce.pethealth.appointment_function.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.xforce.pethealth.appointment_function.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/pet-health/v1/clinics/{clinicId}/veterinarians/{vetId}/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Appointments", description = "Appointment Management Endpoints")
public class AppointmentsController {
    private final AppointmentCommandService appointmentCommandService;
    private final AppointmentQueryService appointmentQueryService;

    public AppointmentsController(AppointmentCommandService appointmentCommandService, AppointmentQueryService appointmentQueryService) {
        this.appointmentCommandService = appointmentCommandService;
        this.appointmentQueryService = appointmentQueryService;
    }

    @PostMapping
    public ResponseEntity<AppointmentResource> createAppointment(@PathVariable("clinicId") Long clinicId, @PathVariable("vetId") Long vetId, @RequestBody CreateAppointmentResource resource) {
        var createAppointmentCommand = CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(clinicId, vetId, resource);
        Long appointmentId = appointmentCommandService.handle(createAppointmentCommand);

        if (appointmentId == null || appointmentId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(appointmentId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);

        if (appointment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return new ResponseEntity<>(appointmentResource, HttpStatus.CREATED);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResource> getAppointmentById(@PathVariable Long appointmentId) {
        var getAppointmentByIdQuery = new GetAppointmentByIdQuery(appointmentId);
        var appointment = appointmentQueryService.handle(getAppointmentByIdQuery);
        if (appointment.isEmpty()) return ResponseEntity.badRequest().build();
        var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment.get());
        return ResponseEntity.ok(appointmentResource);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentResource>> getAllAppointment() {
        var getAllAppointmentsQuery = new GetAllAppointmentsQuery();
        var appointments = appointmentQueryService.handle(getAllAppointmentsQuery);
        var appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(appointmentResources);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResource>> getAppointmentsByClinicAndVet(@PathVariable("clinicId") Long clinicId, @PathVariable("vetId") Long vetId) {
        GetAllAppointmentsByVeterinarianIdQuery query = new GetAllAppointmentsByVeterinarianIdQuery(clinicId, vetId);
        List<Appointment> appointmentList= appointmentQueryService.handle(query);
        List<AppointmentResource> resources = appointmentList.stream()
                .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("clinicId") Long clinicId, @PathVariable("vetId") Long vetId, @PathVariable("appointmentId") Long appointmentId) {
        var deleteAppointmentCommand = new DeleteAppointmentCommand(clinicId, vetId, appointmentId);
        try {
            appointmentCommandService.handle(deleteAppointmentCommand);
            return ResponseEntity.ok("Appointment deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting appointment: " + e.getMessage());
        }
    }
}
