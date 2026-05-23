package com.udc.application.service;

import com.udc.application.port.in.GetAllEmpleadosUseCase;
import com.udc.application.port.out.GetAllEmpleadosPort;
import com.udc.domain.models.empleado.Empleado;
import com.udc.fixtures.EmpleadoPorts;

import java.util.List;

public class GetEmpleadosService implements GetAllEmpleadosUseCase {

    private final GetAllEmpleadosPort getAllPort = EmpleadoPorts.getAllEmpleadosPort;

    @Override
    public List<Empleado> execute() {
        return getAllPort.execute();
    }
}
