package com.xforce.pethealth.appointment_function.application.internal.commandservices;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateVeterinarianCommand;
import com.xforce.pethealth.appointment_function.domain.model.commands.DeleteVeterinarianCommand;
import com.xforce.pethealth.appointment_function.domain.model.entities.Veterinarian;
import com.xforce.pethealth.appointment_function.domain.services.VeterinarianCommandService;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.AppointmentRepository;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.VeterinarianRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class VeterinarianCommandServiceImpl implements VeterinarianCommandService {

    private final VeterinarianRepository veterinarianRepository;
    private final AppointmentRepository appointmentRepository;

    public VeterinarianCommandServiceImpl(VeterinarianRepository veterinarianRepository, AppointmentRepository appointmentRepository) {
        this.veterinarianRepository = veterinarianRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    @Transactional
    public Long handle(CreateVeterinarianCommand command) {
        Veterinarian veterinarian = new Veterinarian(command.firstName(), command.lastName(), command.specialization(), command.phone(), command.email());
        veterinarianRepository.save(veterinarian);
        return veterinarian.getId();
    }

    @Override
    @Transactional
    public void handle(DeleteVeterinarianCommand command) {
        if (!veterinarianRepository.existsById(command.veterinarianId())){
            throw new IllegalArgumentException("Veterinarian not found by id: " + command.veterinarianId());
        }
        appointmentRepository.deleteById(command.veterinarianId());
    }
}
