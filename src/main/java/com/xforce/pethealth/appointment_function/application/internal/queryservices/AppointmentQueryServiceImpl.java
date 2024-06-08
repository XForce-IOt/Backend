package com.xforce.pethealth.appointment_function.application.internal.queryservices;

import com.xforce.pethealth.appointment_function.domain.model.aggregates.Appointment;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllAppointmentsByVeterinarianIdQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllAppointmentsQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAppointmentByIdQuery;
import com.xforce.pethealth.appointment_function.domain.services.AppointmentQueryService;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.AppointmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    @Transactional
    public Optional<Appointment> handle(GetAppointmentByIdQuery query) {
        return appointmentRepository.findById(query.appointmentId());
    }

    @Override
    @Transactional
    public List<Appointment> handle(GetAllAppointmentsQuery query) {
        return appointmentRepository.findAll();
    }

    @Override
    @Transactional
    public List<Appointment> handle(GetAllAppointmentsByVeterinarianIdQuery query) {
        return appointmentRepository.findAllByClinicIdAndVeterinarianId(query.clinicId(), query.veterinarianId());
    }
}
