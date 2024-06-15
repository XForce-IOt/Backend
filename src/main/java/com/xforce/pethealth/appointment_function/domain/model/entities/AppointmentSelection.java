package com.xforce.pethealth.appointment_function.domain.model.entities;

import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import com.xforce.pethealth.appointment_function.domain.model.aggregates.Appointment;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class AppointmentSelection extends AbstractAggregateRoot<AppointmentSelection> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_owner_id", nullable = false)
    @Getter
    private PetOwner petOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    @Getter
    private Appointment appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    @Getter
    private Pet pet;

    public AppointmentSelection(PetOwner petOwner, Pet pet, Appointment appointment) {
        this.petOwner = petOwner;
        this.appointment = appointment;
        this.pet = pet;
    }

    public AppointmentSelection() {}
}
