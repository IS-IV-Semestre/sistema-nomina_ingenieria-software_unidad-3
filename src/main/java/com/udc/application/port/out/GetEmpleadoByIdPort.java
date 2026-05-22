package com.udc.application.port.out;

import com.udc.domain.models.empleado.Empleado;
import com.udc.domain.valueobjects.empleado.EmpleadoId;

public interface GetEmpleadoByIdPort {
    Empleado execute(EmpleadoId id);
}
