package com.xforce.pethealth.appointment_function.domain.model.value_objects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ContactInfo(String phone, String email) {
    public ContactInfo() {
        this(null, null);
    }

    public String getContactInfo() {
        return String.format("%s %s", phone, email);
    }

    public ContactInfo {
        if (phone == null && phone.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or blank");
        }
        if (email == null && email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
    }
}
