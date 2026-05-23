package com.udc.application.port.in;

import com.udc.domain.models.empleado.Empleado;

import java.util.List;

public interface GetAllEmpleadosUseCase {
    List<Empleado> execute();
}
