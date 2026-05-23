package com.udc.application.port.out;

import com.udc.domain.models.empleado.EmpleadoPorHoras;

public interface SaveEmpleadoPorHorasPort {
    EmpleadoPorHoras create(EmpleadoPorHoras empleadoPorHoras);
}
