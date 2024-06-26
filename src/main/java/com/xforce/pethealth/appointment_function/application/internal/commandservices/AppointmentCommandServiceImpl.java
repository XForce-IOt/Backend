package com.xforce.pethealth.appointment_function.application.internal.commandservices;

import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import com.xforce.pethealth.appointment_function.domain.model.aggregates.Appointment;
import com.xforce.pethealth.appointment_function.domain.model.commands.CreateAppointmentCommand;
import com.xforce.pethealth.appointment_function.domain.model.commands.DeleteAppointmentCommand;
import com.xforce.pethealth.appointment_function.domain.model.entities.Veterinarian;
import com.xforce.pethealth.appointment_function.domain.services.AppointmentCommandService;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.AppointmentRepository;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.VeterinarianRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {
    private final AppointmentRepository appointmentRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final PetOwnerRepository petOwnerRepository;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository, VeterinarianRepository veterinarianRepository, PetOwnerRepository petOwnerRepository) {
        this.appointmentRepository = appointmentRepository;
        this.veterinarianRepository = veterinarianRepository;
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    @Transactional
    public Long handle(CreateAppointmentCommand command) {
        Veterinarian veterinarian = veterinarianRepository.findById(command.veterinarianId())
                .orElseThrow(() -> new IllegalArgumentException("Veterinarian not found with ID: " + command.veterinarianId()));

        // Verificar si el veterinario pertenece a la clínica especificada
        if (!veterinarian.getClinic().getId().equals(command.clinicId())) {
            throw new IllegalArgumentException("Veterinarian does not belong to the specified clinic with ID: " + command.clinicId());
        }

        PetOwner petOwner = petOwnerRepository.findById(command.petOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found with ID: " + command.petOwnerId()));

        Pet pet = petOwner.getPets().stream()
                .filter(p -> p.getId().equals(command.petId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Pet not found with ID: " + command.petId()));

        // Crear la nueva cita con la instancia de Veterinarian
        Appointment appointment = new Appointment(veterinarian, pet, command.title(), command.initialStatus(), command.description(), command.dateTime());
        appointmentRepository.save(appointment);
        return appointment.getId();
    }

    @Override
    @Transactional
    public void handle(DeleteAppointmentCommand command) {
        Appointment appointment = appointmentRepository.findById(command.appointmentId())
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + command.appointmentId()));

        // Verificar que el veterinario, la clínica y el pet correspondan
        if (!appointment.getVeterinarian().getId().equals(command.veterinarianId()) ||
                !appointment.getVeterinarian().getClinic().getId().equals(command.clinicId()) ||
                !appointment.getPet().getId().equals(command.petId())) {
            throw new IllegalArgumentException("Mismatch in clinic, veterinarian, pet owner, or pet ID");
        }

        appointmentRepository.delete(appointment);
    }
}
