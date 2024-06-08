package com.xforce.pethealth.appointment_function.application.internal.queryservices;

import com.xforce.pethealth.appointment_function.domain.model.entities.Clinic;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllClinicsQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetClinicByIdQuery;
import com.xforce.pethealth.appointment_function.domain.services.ClinicQueryService;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.ClinicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicQueryServiceImpl implements ClinicQueryService {
    private final ClinicRepository clinicRepository;

    public ClinicQueryServiceImpl(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public Optional<Clinic> handle(GetClinicByIdQuery query) {
        return clinicRepository.findById(query.clinicId());
    }

    @Override
    public List<Clinic> handle(GetAllClinicsQuery query) {
        return clinicRepository.findAll();
    }
}
