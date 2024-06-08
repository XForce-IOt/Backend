package com.xforce.pethealth.appointment_function.domain.services;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateAppointmentSelectionCommand;
import com.xforce.pethealth.appointment_function.domain.model.commands.DeleteAppointmentSelectionCommand;

public interface AppointmentSelectionCommandService {
    Long handle(CreateAppointmentSelectionCommand command);
    void handle(DeleteAppointmentSelectionCommand command);
}
