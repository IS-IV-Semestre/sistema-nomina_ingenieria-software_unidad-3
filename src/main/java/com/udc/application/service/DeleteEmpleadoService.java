package com.udc.application.service;

import com.udc.application.port.in.DeleteEmpleadoUseCase;
import com.udc.application.port.out.DeleteEmpleadoPort;
import com.udc.application.service.dto.command.DeleteEmpleadoCommand;
import com.udc.domain.exceptions.empleado.InvalidEmpleadoId;
import com.udc.domain.valueobjects.empleado.EmpleadoId;
import com.udc.fixtures.EmpleadoPorts;

public class DeleteEmpleadoService implements DeleteEmpleadoUseCase {

    private final DeleteEmpleadoPort deleteEmpleadoPort = EmpleadoPorts.deleteEmpleadoPort;

    @Override
    public void execute(DeleteEmpleadoCommand command) {
        if (EmpleadoPorts.getAllEmpleadosPort.execute().isEmpty()) {
            throw new RuntimeException("No hay empleados registrados para eliminar.");
        }

        if (command.id() == null) {
            throw InvalidEmpleadoId.becauseIsEmpty();
        }

        deleteEmpleadoPort.delete(new EmpleadoId(command.id()));
    }

}
