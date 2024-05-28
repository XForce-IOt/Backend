package com.xforce.pethealth.appointment_function.domain.model.entities;

import com.xforce.pethealth.appointment_function.domain.model.aggregates.Appointment;
import com.xforce.pethealth.appointment_function.domain.model.value_objects.ContactInfo;
import com.xforce.pethealth.appointment_function.domain.model.value_objects.FullName;
import com.xforce.pethealth.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Veterinarian extends AbstractAggregateRoot<Veterinarian> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Column(nullable = false)
    private String specialization;

    @Embedded
    private FullName name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "phone", column = @Column(name = "contact_phone")),
            @AttributeOverride(name = "email", column = @Column(name = "contact_email"))
    })
    private ContactInfo contactInfo;

    @OneToMany(mappedBy = "veterinarian", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    public Veterinarian(String firstName, String lastName, String specialization, String phone, String email) {
        this.name = new FullName(firstName, lastName);
        this.specialization = specialization;
        this.contactInfo = new ContactInfo(phone, email);
    }

    public Veterinarian() {}

    public String getFullName() {
        return this.name.getFullName();
    }

    public String getContactInfo() {
        return contactInfo.getContactInfo();
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
