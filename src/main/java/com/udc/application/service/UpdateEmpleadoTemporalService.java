package com.udc.application.service;

import com.udc.application.port.in.UpdateEmpleadoTemporalUseCase;
import com.udc.application.port.out.UpdateEmpleadoTemporalPort;
import com.udc.application.service.dto.command.UpdateEmpleadoTemporalCommand;
import com.udc.domain.models.empleado.EmpleadoTemporal;
import com.udc.fixtures.EmpleadoPorts;

public class UpdateEmpleadoTemporalService implements UpdateEmpleadoTemporalUseCase {

    private final UpdateEmpleadoTemporalPort updatePort = EmpleadoPorts.updateEmpleadoTemporalPort;

    @Override
    public void execute(UpdateEmpleadoTemporalCommand command) {
        if (EmpleadoPorts.getAllEmpleadosPort.execute().isEmpty()) {
            throw new RuntimeException("No hay empleados registrados para actualizar.");
        }

        if (command.id() == null) {
            throw new IllegalArgumentException("El ID del empleado no puede ser nulo.");
        }

        if (command.salarioMensualFijo() <= 0) {
            throw new IllegalArgumentException("El salario mensual fijo debe ser mayor a cero.");
        }

        if (command.duracionContrato() <= 0) {
            throw new IllegalArgumentException("La duración del contrato debe ser mayor a cero.");
        }

        EmpleadoTemporal empleadoTemporalActualizado = new EmpleadoTemporal(
                command.nombre(),
                command.apellidos(),
                command.duracionContrato(),
                command.tipoDocumento(),
                command.documento(),
                command.salarioMensualFijo()
        );
        updatePort.update(empleadoTemporalActualizado);
    }
}
