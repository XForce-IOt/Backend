package com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories;

import com.xforce.pethealth.appointment_function.domain.model.entities.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
    List<Veterinarian> findByClinicId(Long clinicId);
}
