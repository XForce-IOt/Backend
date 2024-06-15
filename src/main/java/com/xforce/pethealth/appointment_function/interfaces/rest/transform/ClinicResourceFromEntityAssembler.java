package com.xforce.pethealth.appointment_function.interfaces.rest.transform;

import com.xforce.pethealth.appointment_function.domain.model.entities.Clinic;
import com.xforce.pethealth.appointment_function.interfaces.rest.resources.ClinicResource;

public class ClinicResourceFromEntityAssembler {
    public static ClinicResource toResourceFromEntity(Clinic entity) {
        return new ClinicResource(entity.getId(), entity.getRating(), entity.getName(), entity.getNumber(), entity.getSocialMedia(), entity.getProfilePicture());
    }
}
