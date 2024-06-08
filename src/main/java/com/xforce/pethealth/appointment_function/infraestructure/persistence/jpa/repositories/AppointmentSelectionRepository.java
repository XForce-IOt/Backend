package com.xforce.pethealth.appointment_function.infraestructure.persistence.jpa.repositories;

import com.xforce.pethealth.appointment_function.domain.model.entities.AppointmentSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentSelectionRepository extends JpaRepository<AppointmentSelection, Long> {
    List<AppointmentSelection> findAllByPetOwnerId(Long petOwnerId);
    Optional<AppointmentSelection> findByPetOwnerIdAndId(Long petOwnerId, Long id);
}
