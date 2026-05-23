package com.udc.application.port.in;

import com.udc.application.service.dto.command.CreateEmpleadoPorHorasCommand;
import com.udc.domain.models.empleado.EmpleadoPorHoras;

public interface CreateEmpleadoPorHorasUseCase {
    EmpleadoPorHoras execute(CreateEmpleadoPorHorasCommand command);
}
