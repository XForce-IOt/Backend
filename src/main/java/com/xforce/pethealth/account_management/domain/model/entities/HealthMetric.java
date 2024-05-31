package com.xforce.pethealth.account_management.domain.model.entities;

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
@Table(name = "health_metrics")
public class HealthMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
    @JoinColumn(name = "neck_id")
    private Neck neck;
}