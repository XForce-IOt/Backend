package com.xforce.pethealth.appointment_function.application.internal.commandservices;

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

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository, VeterinarianRepository veterinarianRepository) {
        this.appointmentRepository = appointmentRepository;
        this.veterinarianRepository = veterinarianRepository;
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

        // Crear la nueva cita con la instancia de Veterinarian
        Appointment appointment = new Appointment(veterinarian, command.initialStatus(), command.description(), command.dateTime());
        appointmentRepository.save(appointment);
        return appointment.getId();
    }

    @Override
    @Transactional
    public void handle(DeleteAppointmentCommand command) {
        Appointment appointment = appointmentRepository.findById(command.appointmentId())
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + command.appointmentId()));

        // Verificar que el veterinario y la clínica correspondan
        if (!appointment.getVeterinarian().getId().equals(command.veterinarianId()) ||
                !appointment.getVeterinarian().getClinic().getId().equals(command.clinicId())) {
            throw new IllegalArgumentException("Mismatch in clinic or veterinarian ID");
        }

        appointmentRepository.delete(appointment);
    }
}
