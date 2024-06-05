package com.xforce.pethealth.account_management.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.domain.model.commands.AddPetCommand;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Pet extends AbstractAggregateRoot<Pet>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specie;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String sex;

    @Column (nullable = false)
    private String size;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double perimeter;

    @Column(nullable = false)
    private String image;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petOwner_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private PetOwner petOwner;

    protected Pet() {}

    public Pet(PetOwner petOwner, AddPetCommand command) {
        this.petOwner = petOwner;
        this.name = command.name();
        this.specie = command.specie();
        this.age = command.age();
        this.sex = command.sex();
        this.size = command.size();
        this.weight = command.weight();
        this.perimeter = command.perimeter();
        this.image = command.image();
    }
}

