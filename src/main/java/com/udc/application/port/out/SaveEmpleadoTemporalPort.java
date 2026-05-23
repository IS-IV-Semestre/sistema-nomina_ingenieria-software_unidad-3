package com.udc.application.port.out;

import com.udc.domain.models.empleado.EmpleadoTemporal;

public interface SaveEmpleadoTemporalPort {
    EmpleadoTemporal create(EmpleadoTemporal empleadoTemporal);
}
