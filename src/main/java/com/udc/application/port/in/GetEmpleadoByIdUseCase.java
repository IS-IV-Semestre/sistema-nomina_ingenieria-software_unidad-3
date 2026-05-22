package com.udc.application.port.in;

import com.udc.application.service.dto.query.GetEmpleadoByIdQuery;
import com.udc.domain.models.empleado.Empleado;

public interface GetEmpleadoByIdUseCase {
    Object execute(GetEmpleadoByIdQuery query);
}
