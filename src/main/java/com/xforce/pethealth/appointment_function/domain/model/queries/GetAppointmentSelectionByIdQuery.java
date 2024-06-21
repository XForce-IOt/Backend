package com.xforce.pethealth.appointment_function.domain.model.queries;

public record GetAppointmentSelectionByIdQuery(Long petOwnerId, Long petId, Long appointmentSelectionId) {
}
