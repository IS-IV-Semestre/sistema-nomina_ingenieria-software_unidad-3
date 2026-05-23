package com.udc.application.port.out;

import com.udc.domain.models.empleado.EmpleadoPorComision;

public interface SaveEmpleadoPorComisionPort {
    EmpleadoPorComision create(EmpleadoPorComision empleadoPorComision);
}
