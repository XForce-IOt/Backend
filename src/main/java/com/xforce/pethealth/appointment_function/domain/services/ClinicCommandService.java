package com.xforce.pethealth.appointment_function.domain.services;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateClinicCommand;
import com.xforce.pethealth.appointment_function.domain.model.commands.DeleteClinicCommand;

public interface ClinicCommandService {
    Long handle(CreateClinicCommand command);
    void handle(DeleteClinicCommand command);
}
