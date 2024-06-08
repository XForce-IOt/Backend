package com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories;

import com.xforce.pethealth.appointment_function.domain.model.entities.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
