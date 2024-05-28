package com.xforce.pethealth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="necks")
public class Neck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "heartRate", nullable = false)
    private Float heartRate;

    @Column(name = "physicalActivity", nullable = false)
    private Float physicalActivity;

    @Column(name = "temperature", nullable = false)
    private Float temperature;

    @Column(name = "sleepQuality", nullable = false)
    private Float sleepQuality;

    @Column(name = "hydrationLevel", nullable = false)
    private Float hydrationLevel;

    @Column(name = "latitude", nullable = false)
    private Float latitude;

    @Column(name = "longitude", nullable = false)
    private Float longitude;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false,
        foreignKey = @ForeignKey(name = "FK_PET_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Pet pet;
}
