package com.udc.application.port.out;

import com.udc.domain.models.empleado.EmpleadoAsalariado;

public interface UpdateEmpleadoAsalariadoPort {
    EmpleadoAsalariado update(EmpleadoAsalariado empleadoAsalariado);
}
