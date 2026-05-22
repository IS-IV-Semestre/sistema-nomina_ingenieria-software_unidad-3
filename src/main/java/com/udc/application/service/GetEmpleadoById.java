package com.udc.application.service;

import com.udc.application.port.in.GetEmpleadoByIdUseCase;
import com.udc.application.port.out.GetEmpleadoByIdPort;
import com.udc.application.service.dto.query.GetEmpleadoByIdQuery;
import com.udc.domain.exceptions.empleado.InvalidEmpleadoId;
import com.udc.domain.models.empleado.Empleado;
import com.udc.domain.valueobjects.empleado.EmpleadoId;
import com.udc.fixtures.EmpleadoPorts;

public class GetEmpleadoById implements GetEmpleadoByIdUseCase {

    private final GetEmpleadoByIdPort getEmpleadoByIdPort = EmpleadoPorts.getEmpleadoByIdPort;

    @Override
    public Empleado execute(GetEmpleadoByIdQuery query) {
        if (EmpleadoPorts.getAllEmpleadosPort.execute().isEmpty()) {
            throw new RuntimeException("No hay empleados registrados.");
        }

        if(query.id() != null) {
            return getEmpleadoByIdPort.execute(new EmpleadoId(query.id()));
        }else{
            throw InvalidEmpleadoId.becauseIsEmpty();
        }
    }
}
