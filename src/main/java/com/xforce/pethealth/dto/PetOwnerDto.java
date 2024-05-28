package com.xforce.pethealth.dto;

import lombok.Data;

@Data
public class PetOwnerDto {
    private Long id;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String password;
    private String image;
}
