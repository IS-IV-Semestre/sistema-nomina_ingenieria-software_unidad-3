package com.udc.application.port.out;

import com.udc.domain.valueobjects.empleado.EmpleadoId;

public interface DeleteEmpleadoPort {
    void delete(EmpleadoId id);
}
