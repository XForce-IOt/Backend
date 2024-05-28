package com.xforce.pethealth.appointment_function.application.internal.queryservices;

import com.xforce.pethealth.appointment_function.domain.model.entities.Veterinarian;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetAllVeterinarianQuery;
import com.xforce.pethealth.appointment_function.domain.model.queries.GetVeterinarianByIdQuery;
import com.xforce.pethealth.appointment_function.domain.services.VeterinarianQueryService;
import com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories.VeterinarianRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarianQueryServiceImpl implements VeterinarianQueryService {

    private final VeterinarianRepository veterinarianRepository;

    public VeterinarianQueryServiceImpl(VeterinarianRepository veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    @Override
    @Transactional
    public Optional<Veterinarian> handle(GetVeterinarianByIdQuery query) {
        return veterinarianRepository.findById(query.veterinarianId());
    }

    @Override
    @Transactional
    public List<Veterinarian> handle(GetAllVeterinarianQuery query) {
        return veterinarianRepository.findAll();
    }
}
