package com.xforce.pethealth.appointment_function.application.internal.commandservices;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateClinicCommand;
import com.xforce.pethealth.appointment_function.domain.model.commands.DeleteClinicCommand;
import com.xforce.pethealth.appointment_function.domain.model.entities.Clinic;
import com.xforce.pethealth.appointment_function.domain.services.ClinicCommandService;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.ClinicRepository;
import org.springframework.stereotype.Service;

@Service
public class ClinicCommandServiceImpl implements ClinicCommandService {
    private final ClinicRepository clinicRepository;

    public ClinicCommandServiceImpl(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public Long handle(CreateClinicCommand command) {
        Clinic clinic = new Clinic(command.rating(), command.name(), command.number(), command.socialMedia());
        clinicRepository.save(clinic);
        return clinic.getId();
    }

    @Override
    public void handle(DeleteClinicCommand command) {
        if (!clinicRepository.existsById(command.clinicId())) {
            throw new IllegalArgumentException("Clinic with id " + command.clinicId() + " does not exist");
        }
        clinicRepository.deleteById(command.clinicId());
    }
}
