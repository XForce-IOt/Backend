package com.xforce.pethealth.appointment_function.domain.model.commands;

import com.xforce.pethealth.appointment_function.domain.model.value_objects.ProgressStatus;

public record CreateAppointmentCommand(
        Long veterinarianId,        // ID del veterinario que ofrece la cita
        String dateTime,     // Fecha y hora de la cita
        String description,         // Descripción de la cita
        ProgressStatus initialStatus // Estado inicial de la cita, utilizando el enum
        //Long petId
) {
}
