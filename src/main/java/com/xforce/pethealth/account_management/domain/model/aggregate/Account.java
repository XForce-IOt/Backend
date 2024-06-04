package com.xforce.pethealth.account_management.domain.model.aggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xforce.pethealth.account_management.domain.model.entities.PetOwner;
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
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "paymentMethod", nullable = false)
    private String paymentMethod;

    @Column(name = "paymentDate", nullable = false)
    private String paymentDate;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pet_owner_id")
    private PetOwner petOwner;
}