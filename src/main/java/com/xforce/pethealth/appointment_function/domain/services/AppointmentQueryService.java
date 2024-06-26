package com.xforce.pethealth.appointment_function.domain.services;

import com.xforce.pethealth.appointment_function.domain.model.aggregates.Appointment;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllAppointmentsByPetOwnerIdQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllAppointmentsByVeterinarianIdQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllAppointmentsQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAppointmentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AppointmentQueryService {
    Optional<Appointment> handle(GetAppointmentByIdQuery query);
    List<Appointment> handle(GetAllAppointmentsQuery query);
    List<Appointment> handle(GetAllAppointmentsByVeterinarianIdQuery query);
    List<Appointment> handle(GetAllAppointmentsByPetOwnerIdQuery query);
}
