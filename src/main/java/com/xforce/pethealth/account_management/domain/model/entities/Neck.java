package com.xforce.pethealth.account_management.domain.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="necks")
public class Neck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "serialNumber", nullable = false)
    private String serialNumber;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pet_id", foreignKey = @ForeignKey(name = "FK_neck_pet"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Pet pet;

    @JsonIgnore
    @OneToMany(mappedBy = "neck", cascade = CascadeType.ALL)
    private List<HealthMetric> healthMetrics = new ArrayList<>();
}

