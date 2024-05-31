package com.xforce.pethealth.entity;

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
@Table(name="pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "specie", nullable = false)
    private String specie;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column (name = "size", nullable = false)
    private String size;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "perimeter", nullable = false)
    private double perimeter;

    @Column(name = "image", nullable = false)
    private String image;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pet_owner_id", nullable = false)
    private PetOwner petOwner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "neck_id", referencedColumnName = "id")
    private Neck neck = new Neck();
}
