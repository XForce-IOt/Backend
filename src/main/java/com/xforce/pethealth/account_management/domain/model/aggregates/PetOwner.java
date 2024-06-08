package com.xforce.pethealth.account_management.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xforce.pethealth.account_management.domain.model.commands.CreatePetOwnerCommand;
import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import com.xforce.pethealth.account_management.domain.model.entities.Subscription;
import com.xforce.pethealth.appointment_function.domain.model.entities.AppointmentSelection;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class PetOwner extends AbstractAggregateRoot<PetOwner>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "petOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "petOwner", cascade = CascadeType.ALL)
    private Subscription subscription;

    @JsonIgnore
    @OneToMany(mappedBy = "petOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentSelection> appointmentSelections = new ArrayList<>();

    protected PetOwner() {}

    public PetOwner(CreatePetOwnerCommand command) {
        this.name = command.name();
        this.lastName = command.lastName();
        this.address = command.address();
        this.phone = command.phone();
        this.email = command.email();
        this.password = command.password();
        this.image = command.image();
    }
}
