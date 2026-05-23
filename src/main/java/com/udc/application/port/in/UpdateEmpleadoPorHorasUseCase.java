package com.udc.application.port.in;

import com.udc.application.service.dto.command.UpdateEmpleadoPorHorasCommand;

public interface UpdateEmpleadoPorHorasUseCase {
    void execute(UpdateEmpleadoPorHorasCommand command);
}
