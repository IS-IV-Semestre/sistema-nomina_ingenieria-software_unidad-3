package com.udc.application.port.in;

import com.udc.application.service.dto.command.UpdateEmpleadoTemporalCommand;

public interface UpdateEmpleadoTemporalUseCase {
    void execute(UpdateEmpleadoTemporalCommand command);
}
