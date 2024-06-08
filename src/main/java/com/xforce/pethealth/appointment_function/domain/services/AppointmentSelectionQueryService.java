package com.xforce.pethealth.appointment_function.domain.services;

import com.xforce.pethealth.appointment_function.domain.model.entities.AppointmentSelection;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllAppointmentSelectionsByPetOwnerIdQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAppointmentSelectionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AppointmentSelectionQueryService {
    Optional<AppointmentSelection> handle(GetAppointmentSelectionByIdQuery query);
    List<AppointmentSelection> handle(GetAllAppointmentSelectionsByPetOwnerIdQuery query);
}
