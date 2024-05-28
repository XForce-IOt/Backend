package com.xforce.pethealth.entity;

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
@Table(name="pet_owners")
public class PetOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "address", nullable = false, length = 22)
    private String address;

    @Column(name = "phone", nullable = false, length = 9)
    private String phone;

    @Column(name = "email", nullable = false, length = 22)
    private String email;

    @Column(name = "password", nullable = false, length = 22)
    private String password;

    @Column(name = "image", nullable = false)
    private String image;
}
