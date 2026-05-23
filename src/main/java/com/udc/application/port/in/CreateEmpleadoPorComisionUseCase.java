package com.udc.application.port.in;

import com.udc.application.service.dto.command.CreateEmpleadoPorComisionCommand;
import com.udc.domain.models.empleado.EmpleadoPorComision;

public interface CreateEmpleadoPorComisionUseCase {
    EmpleadoPorComision execute(CreateEmpleadoPorComisionCommand command);
}
