package com.xforce.pethealth.function_collar.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.xforce.pethealth.account_management.domain.model.aggregates.PetOwner;
import com.xforce.pethealth.account_management.domain.model.entities.Pet;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class SensorData extends AbstractAggregateRoot<SensorData> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Column(nullable = false)
    private double temperature;

    @Getter
    @Column(nullable = false)
    private double humidity;

    @Getter
    @Column(nullable = false)
    private double distance;

    @Getter
    @Column(nullable = false)
    private double pulse;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_owner_id", nullable = false)
    @Getter
    private PetOwner petOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    @Getter
    private Pet pet;

    public SensorData(PetOwner petOwner, Pet pet, double temperature, double humidity, double distance, double pulse) {
        this.petOwner = petOwner;
        this.pet = pet;
        this.temperature = temperature;
        this.humidity = humidity;
        this.distance = distance;
        this.pulse = pulse;
    }

    public SensorData() {}
}
