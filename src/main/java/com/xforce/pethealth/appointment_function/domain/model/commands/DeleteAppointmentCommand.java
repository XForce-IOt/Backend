package com.xforce.pethealth.appointment_function.domain.model.commands;

public record DeleteAppointmentCommand(Long clinicId, Long veterinarianId, Long petOwnerId, Long petId, Long appointmentId) {
}
