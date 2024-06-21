package com.xforce.pethealth.appointment_function.domain.model.aggregates;

import com.xforce.pethealth.appointment_function.domain.model.entities.Veterinarian;
import com.xforce.pethealth.appointment_function.domain.model.value_objects.ProgressStatus;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Appointment extends AbstractAggregateRoot<Appointment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String title;

    @Column(nullable = false)
    @Getter
    private ProgressStatus status;

    @Column(nullable = false)
    @Getter
    private String description;

    @Column(nullable = false)
    @Getter
    private String dateTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "veterinarian_id", nullable = false)
    @Getter
    private Veterinarian veterinarian;

    public Appointment(Veterinarian veterinarian, String title, ProgressStatus status, String description, String dateTime) {
        this.veterinarian = veterinarian;
        this.title = title;
        this.status = status;
        this.description = description;
        this.dateTime = dateTime;
    }

    public Appointment() {

    }

    public void start() {
        this.status = ProgressStatus.STARTED;
    }
    public void complete() {
        this.status = ProgressStatus.FINISHED;
    }

    public boolean isCompleted() {
        return this.status == ProgressStatus.FINISHED;
    }
    public boolean isInProgress() { return status == ProgressStatus.STARTED; }
    public boolean isNotProgress() { return status == ProgressStatus.NOT_STARTED; }


}
