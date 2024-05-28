package com.xforce.pethealth.appointment_function.interfaces.rest.resources;

import com.xforce.pethealth.appointment_function.domain.model.value_objects.ProgressStatus;

import java.time.LocalDateTime;

public record CreateAppointmentResource(
        Long veterinarianId,
        String dateTime,
        String description,
        ProgressStatus initialStatus
) {
}
