package com.xforce.pethealth.appointment_function.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Clinic extends AbstractAggregateRoot<Veterinarian> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Column(nullable = false)
    private double rating;

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    @Column(nullable = false)
    private String number;

    @Getter
    @Column(name = "social_media", nullable = false)
    private String socialMedia;

    @Getter
    @Column(name = "profile_picture", nullable = false)
    private String profilePicture;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Veterinarian> veterinarians;

    public Clinic(double rating, String name, String number, String socialMedia, String profilePicture) {
        this.rating = rating;
        this.name = name;
        this.number = number;
        this.socialMedia = socialMedia;
        this.profilePicture = profilePicture;
    }

    public Clinic() {}
}
