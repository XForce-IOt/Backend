package com.xforce.pethealth.appointment_function.domain.services;

import com.xforce.pethealth.appointment_function.domain.model.entities.Clinic;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllClinicsQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetClinicByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ClinicQueryService {
    Optional<Clinic> handle(GetClinicByIdQuery query);
    List<Clinic> handle(GetAllClinicsQuery query);
}
