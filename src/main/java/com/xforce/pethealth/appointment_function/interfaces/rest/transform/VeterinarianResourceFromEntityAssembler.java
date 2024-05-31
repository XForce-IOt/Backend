package com.xforce.pethealth.appointment_function.interfaces.rest.transform;

import com.xforce.pethealth.appointment_function.domain.model.entities.Veterinarian;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.VeterinarianResource;

public class VeterinarianResourceFromEntityAssembler {
    public static VeterinarianResource toResourceFromEntity(Veterinarian entity) {
        return new VeterinarianResource(entity.getId(), entity.getFullName(), entity.getSpecialization(), entity.getContactInfo());
    }
}
