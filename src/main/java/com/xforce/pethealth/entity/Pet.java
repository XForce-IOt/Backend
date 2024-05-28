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
@Table(name="pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 22)
    private String name;

    @Column(name = "specie", nullable = false, length = 22)
    private String specie;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "sex", nullable = false, length = 10)
    private String sex;

    @Column (name = "size", nullable = false, length = 10)
    private String size;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "perimeter", nullable = false)
    private double perimeter;

    @Column(name = "image", nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "pet_owner_id", nullable = false,
        foreignKey = @ForeignKey(name = "FK_PET_OWNER_ID"))
    private PetOwner petOwner;

    @ManyToOne
    @JoinColumn(name = "neck_id", nullable = false,
        foreignKey = @ForeignKey(name = "FK_NECK_ID"))
    private Neck neck;
}
