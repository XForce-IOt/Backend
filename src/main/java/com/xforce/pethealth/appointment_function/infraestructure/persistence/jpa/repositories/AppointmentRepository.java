package com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories;

import com.xforce.pethealth.appointment_function.domain.model.aggregates.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
