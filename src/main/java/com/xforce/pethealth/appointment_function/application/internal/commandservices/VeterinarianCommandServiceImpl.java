package com.xforce.pethealth.appointment_function.application.internal.commandservices;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateVeterinarianCommand;
import com.xforce.pethealth.appointment_function.domain.model.commands.DeleteVeterinarianCommand;
import com.xforce.pethealth.appointment_function.domain.model.entities.Clinic;
import com.xforce.pethealth.appointment_function.domain.model.entities.Veterinarian;
import com.xforce.pethealth.appointment_function.domain.services.VeterinarianCommandService;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.AppointmentRepository;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.ClinicRepository;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.VeterinarianRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class VeterinarianCommandServiceImpl implements VeterinarianCommandService {

    private final VeterinarianRepository veterinarianRepository;
    private final ClinicRepository clinicRepository;

    public VeterinarianCommandServiceImpl(VeterinarianRepository veterinarianRepository, ClinicRepository clinicRepository) {
        this.veterinarianRepository = veterinarianRepository;
        this.clinicRepository = clinicRepository;
    }

    @Override
    @Transactional
    public Long handle(CreateVeterinarianCommand command) {
        Clinic clinic = clinicRepository.findById(command.clinicId())
                .orElseThrow(() -> new RuntimeException("Clinic not found with ID: " + command.clinicId()));

        Veterinarian veterinarian = new Veterinarian(clinic, command.firstName(), command.lastName(), command.password(), command.specialization(), command.phone(), command.email());
        veterinarian = veterinarianRepository.save(veterinarian);
        return veterinarian.getId();
    }

    @Override
    @Transactional
    public void handle(DeleteVeterinarianCommand command) {
        Veterinarian veterinarian = veterinarianRepository.findById(command.veterinarianId())
                .orElseThrow(() -> new IllegalArgumentException("Veterinarian not found by id: " + command.veterinarianId()));

        if (veterinarian.getClinic().getId().equals(command.clinicId())) {
            veterinarianRepository.delete(veterinarian);
        } else {
            throw new IllegalArgumentException("Veterinarian does not belong to the specified clinic");
        }
    }
}
