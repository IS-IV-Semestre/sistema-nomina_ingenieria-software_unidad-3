package com.udc.application.port.in;

import com.udc.application.service.dto.command.DeleteEmpleadoCommand;

public interface DeleteEmpleadoUseCase {
    void execute(DeleteEmpleadoCommand command);
}
