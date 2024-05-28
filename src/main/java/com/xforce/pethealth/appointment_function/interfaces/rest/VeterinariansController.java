package com.xforce.pethealth.appointment_function.interfaces.rest;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateVeterinarianCommand;
import com.xforce.pethealth.appointment_function.domain.model.commands.DeleteVeterinarianCommand;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllVeterinarianQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetVeterinarianByIdQuery;
import com.xforce.pethealth.appointment_function.domain.services.VeterinarianCommandService;
import com.xforce.pethealth.appointment_function.domain.services.VeterinarianQueryService;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.CreateVeterinarianResource;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.VeterinarianResource;
import com.xforce.pethealth.appointment_function.interfaces.rest.transform.CreateVeterinarianCommandFromResourceAssembler;
import com.xforce.pethealth.appointment_function.interfaces.rest.transform.VeterinarianResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/veterinarians", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Veterinarians", description = "Veterinarians Management Endpoints")
public class VeterinariansController {

    private final VeterinarianCommandService veterinarianCommandService;
    private final VeterinarianQueryService veterinarianQueryService;

    public VeterinariansController(VeterinarianCommandService veterinarianCommandService, VeterinarianQueryService veterinarianQueryService) {
        this.veterinarianCommandService = veterinarianCommandService;
        this.veterinarianQueryService = veterinarianQueryService;
    }

    @PostMapping
    public ResponseEntity<VeterinarianResource> createVeterinarian(@RequestBody CreateVeterinarianResource createVeterinarianResource) {
        var createVeterinarianCommand = CreateVeterinarianCommandFromResourceAssembler.toCommandFromResource(createVeterinarianResource);
        var veterinarianId = veterinarianCommandService.handle(createVeterinarianCommand);
        if (veterinarianId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getVeterinarianByIdQuery = new GetVeterinarianByIdQuery(veterinarianId);
        var veterinarian = veterinarianQueryService.handle(getVeterinarianByIdQuery);

        if(veterinarian.isEmpty()) return ResponseEntity.badRequest().build();

        var veterinarianResource = VeterinarianResourceFromEntityAssembler.toResourceFromEntity(veterinarian.get());
        return new ResponseEntity<>(veterinarianResource, HttpStatus.CREATED);
    }

    @GetMapping("/{veterinarianId}")
    public ResponseEntity<VeterinarianResource> getVeterinarianById(@PathVariable Long veterinarianId){
        var getVeterinarianByIdQuery = new GetVeterinarianByIdQuery(veterinarianId);
        var veterinarian = veterinarianQueryService.handle(getVeterinarianByIdQuery);
        if(veterinarian.isEmpty()) return ResponseEntity.badRequest().build();
        var veterinarianResource = VeterinarianResourceFromEntityAssembler.toResourceFromEntity(veterinarian.get());
        return ResponseEntity.ok(veterinarianResource);
    }

    @GetMapping
    public ResponseEntity<List<VeterinarianResource>> getAllVeterinarian(){
        var getAllVeterinarianQuery = new GetAllVeterinarianQuery();
        var veterinarians = veterinarianQueryService.handle(getAllVeterinarianQuery);
        var veterinarianResources = veterinarians.stream().map(VeterinarianResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(veterinarianResources);
    }

    @DeleteMapping("/{veterinarianId}")
    public ResponseEntity<?> deleteVeterinarian(@PathVariable Long veterinarianId){
        var deleteVeterinarianCommand = new DeleteVeterinarianCommand(veterinarianId);
        veterinarianCommandService.handle(deleteVeterinarianCommand);
        return ResponseEntity.ok("Course with given id has been deleted");
    }
}
