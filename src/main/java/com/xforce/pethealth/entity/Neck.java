package com.xforce.pethealth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "serialNumber", nullable = false)
    private String serialNumber;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @JsonIgnore
    @OneToMany(mappedBy = "neck", cascade = CascadeType.ALL)
    private List<HealthMetric> healthMetrics = new ArrayList<>();
}
