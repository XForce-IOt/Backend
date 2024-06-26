package com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories;

import com.xforce.pethealth.appointment_function.domain.model.aggregates.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.veterinarian.clinic.id = :clinicId AND a.veterinarian.id = :vetId")
    List<Appointment> findAllByClinicIdAndVeterinarianId(@Param("clinicId") Long clinicId, @Param("vetId") Long vetId);
    List<Appointment> findByPet_PetOwner_Id(Long petOwnerId);
}
