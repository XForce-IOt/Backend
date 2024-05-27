package com.xforce.pethealth.appointment_function.domain.services;

import com.xforce.pethealth.appointment_function.domain.model.commands.CreateVeterinarianCommand;

public interface VeterinarianCommandService {
    Long handle(CreateVeterinarianCommand command);
}
