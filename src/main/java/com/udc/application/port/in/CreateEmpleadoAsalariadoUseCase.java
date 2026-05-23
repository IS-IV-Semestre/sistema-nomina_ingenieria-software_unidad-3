package com.udc.application.port.in;

import com.udc.application.service.dto.command.CreateEmpleadoAsalariadoCommand;
import com.udc.domain.models.empleado.EmpleadoAsalariado;

public interface CreateEmpleadoAsalariadoUseCase {
    EmpleadoAsalariado execute(CreateEmpleadoAsalariadoCommand command);
}
