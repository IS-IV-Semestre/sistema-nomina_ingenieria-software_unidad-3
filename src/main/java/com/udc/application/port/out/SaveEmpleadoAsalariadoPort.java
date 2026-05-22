package com.udc.application.port.out;

import com.udc.domain.models.empleado.EmpleadoAsalariado;

public interface SaveEmpleadoAsalariadoPort {
    EmpleadoAsalariado create(EmpleadoAsalariado empleadoAsalariado);
}
