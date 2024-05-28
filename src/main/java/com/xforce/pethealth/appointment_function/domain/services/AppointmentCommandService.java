package com.xforce.pethealth.appointment_function.domain.services;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateAppointmentCommand;
import com.xforce.pethealth.appointment_function.domain.model.commands.DeleteAppointmentCommand;

public interface AppointmentCommandService {
    Long handle(CreateAppointmentCommand command);
    void handle(DeleteAppointmentCommand command);
}
