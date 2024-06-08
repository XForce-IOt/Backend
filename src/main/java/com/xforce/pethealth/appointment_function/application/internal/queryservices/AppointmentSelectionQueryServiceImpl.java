package com.xforce.pethealth.appointment_function.application.internal.queryservices;

import com.xforce.pethealth.appointment_function.domain.model.entities.AppointmentSelection;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllAppointmentSelectionsByPetOwnerIdQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAppointmentSelectionByIdQuery;
import com.xforce.pethealth.appointment_function.domain.services.AppointmentSelectionQueryService;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.AppointmentSelectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentSelectionQueryServiceImpl implements AppointmentSelectionQueryService {
    private final AppointmentSelectionRepository appointmentSelectionRepository;

    public AppointmentSelectionQueryServiceImpl(AppointmentSelectionRepository appointmentSelectionRepository) {
        this.appointmentSelectionRepository = appointmentSelectionRepository;
    }

    @Override
    public List<AppointmentSelection> handle(GetAllAppointmentSelectionsByPetOwnerIdQuery query) {
        return appointmentSelectionRepository.findAllByPetOwnerId(query.petOwnerId());
    }

    @Override
    public Optional<AppointmentSelection> handle(GetAppointmentSelectionByIdQuery query) {
        return appointmentSelectionRepository.findByPetOwnerIdAndId(query.petOwnerId(), query.appointmentSelectionId());
    }
}
