package com.udc.application.port.in;

import com.udc.application.service.dto.command.UpdateEmpleadoPorComisionCommand;

public interface UpdateEmpleadoPorComisionUseCase {
    void execute(UpdateEmpleadoPorComisionCommand command);
}
