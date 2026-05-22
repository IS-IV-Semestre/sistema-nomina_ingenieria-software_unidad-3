package com.udc.application.port.out;

import com.udc.domain.models.empleado.Empleado;

import java.util.List;
import java.util.Optional;

public interface GetAllEmpleadosPort {
    Optional<List<Empleado>> execute();
}
