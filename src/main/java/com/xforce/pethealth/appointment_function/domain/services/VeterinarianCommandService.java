package com.xforce.pethealth.appointment_function.domain.services;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateVeterinarianCommand;
import com.xforce.pethealth.appointment_function.domain.model.commands.DeleteVeterinarianCommand;

public interface VeterinarianCommandService {
    Long handle(CreateVeterinarianCommand command);
    void handle(DeleteVeterinarianCommand command);
}
