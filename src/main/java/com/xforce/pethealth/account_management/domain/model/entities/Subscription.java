package com.xforce.pethealth.account_management.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "price", nullable = false)
    private String price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pet_owner_id", nullable = false)
    private PetOwner petOwner;
}
