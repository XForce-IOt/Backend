package com.xforce.pethealth.appointment_function.domain.services;

import com.xforce.pethealth.appointment_function.domain.model.entities.Veterinarian;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllVeterinarianByClinicIdQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllVeterinarianQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetVeterinarianByIdQuery;

import java.util.List;
import java.util.Optional;

public interface VeterinarianQueryService {
    Optional<Veterinarian> handle(GetVeterinarianByIdQuery query);
    List<Veterinarian> handle(GetAllVeterinarianQuery query);
    List<Veterinarian> handle(GetAllVeterinarianByClinicIdQuery query);
}
