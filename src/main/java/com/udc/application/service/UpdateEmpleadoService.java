package com.udc.application.service;

import com.udc.application.port.in.UpdateEmpleadoAsalariadoUseCase;
import com.udc.application.port.out.UpdateEmpleadoAsalariadoPort;
import com.udc.application.service.dto.command.UpdateEmpleadoAsalariadoCommand;
import com.udc.domain.models.empleado.EmpleadoAsalariado;
import com.udc.fixtures.EmpleadoPorts;

public class UpdateEmpleadoService implements UpdateEmpleadoAsalariadoUseCase {

    private final UpdateEmpleadoAsalariadoPort port = EmpleadoPorts.updateEmpleadoAsalariadoPort;

    @Override
    public void execute(UpdateEmpleadoAsalariadoCommand command) {

        if (EmpleadoPorts.getAllEmpleadosPort.execute().isEmpty()) {
            throw new RuntimeException("No hay empleados registrados para actualizar.");
        }

        if (command.id() == null || command.salarioMensual() == 0) {
            throw new IllegalArgumentException("El ID del empleado y el nuevo salario no pueden ser nulos.");
        }

        EmpleadoAsalariado empleadoActualizado = new EmpleadoAsalariado(
                command.id(),
                command.nombre(),
                command.antiguedadAnios(),
                command.tipoDocumento(),
                command.documento(),
                command.salarioMensual()
        );

        port.update(empleadoActualizado);
    }
}
