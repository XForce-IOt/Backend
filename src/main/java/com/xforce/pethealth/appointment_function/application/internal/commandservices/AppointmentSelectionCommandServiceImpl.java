package com.xforce.pethealth.appointment_function.application.internal.commandservices;

import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import com.xforce.pethealth.appointment_function.domain.model.aggregates.Appointment;
import com.xforce.pethealth.appointment_function.domain.model.commands.CreateAppointmentSelectionCommand;
import com.xforce.pethealth.appointment_function.domain.model.commands.DeleteAppointmentSelectionCommand;
import com.xforce.pethealth.appointment_function.domain.model.entities.AppointmentSelection;
import com.xforce.pethealth.appointment_function.domain.services.AppointmentSelectionCommandService;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.AppointmentRepository;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.AppointmentSelectionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AppointmentSelectionCommandServiceImpl implements AppointmentSelectionCommandService {
    private final AppointmentSelectionRepository appointmentSelectionRepository;
    private final PetOwnerRepository petOwnerRepository;
    private final AppointmentRepository appointmentRepository;

    public AppointmentSelectionCommandServiceImpl(AppointmentSelectionRepository appointmentSelectionRepository, PetOwnerRepository petOwnerRepository, AppointmentRepository appointmentRepository) {
        this.appointmentSelectionRepository = appointmentSelectionRepository;
        this.petOwnerRepository = petOwnerRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    @Transactional
    public Long handle(CreateAppointmentSelectionCommand command) {
        PetOwner petOwner = petOwnerRepository.findById(command.petOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found with ID: " + command.petOwnerId()));
        Appointment appointment = appointmentRepository.findById(command.appointmentId())
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + command.appointmentId()));

        AppointmentSelection appointmentSelection = new AppointmentSelection(petOwner, appointment);
        appointmentSelectionRepository.save(appointmentSelection);
        return appointmentSelection.getId();
    }

    @Override
    @Transactional
    public void handle(DeleteAppointmentSelectionCommand command) {
        AppointmentSelection appointmentSelection = appointmentSelectionRepository.findById(command.appointmentSelectionId())
                .orElseThrow(() -> new IllegalArgumentException("Appointment selection not found with ID: " + command.appointmentSelectionId()));

        if (!appointmentSelection.getPetOwner().getId().equals(command.petOwnerId())) {
            throw new IllegalArgumentException("Mismatch in pet owner ID");
        }

        appointmentSelectionRepository.delete(appointmentSelection);
    }
}
