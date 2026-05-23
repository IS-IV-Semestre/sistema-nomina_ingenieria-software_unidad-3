package com.udc.application.port.in;

import com.udc.application.service.dto.command.CreateEmpleadoTemporalCommand;
import com.udc.domain.models.empleado.EmpleadoTemporal;

public interface CreateEmpleadoTemporalUseCase {
    EmpleadoTemporal execute(CreateEmpleadoTemporalCommand command);
}
