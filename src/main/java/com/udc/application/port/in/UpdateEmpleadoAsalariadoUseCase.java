package com.udc.application.port.in;

import com.udc.application.service.dto.command.UpdateEmpleadoAsalariadoCommand;

public interface UpdateEmpleadoAsalariadoUseCase {
    void execute(UpdateEmpleadoAsalariadoCommand command);
}
