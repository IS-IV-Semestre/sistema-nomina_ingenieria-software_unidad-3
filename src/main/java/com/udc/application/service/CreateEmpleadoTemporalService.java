package com.udc.application.service;

import com.udc.application.port.in.CreateEmpleadoTemporalUseCase;
import com.udc.application.port.out.SaveEmpleadoTemporalPort;
import com.udc.application.service.dto.command.CreateEmpleadoTemporalCommand;
import com.udc.domain.models.empleado.EmpleadoTemporal;
import com.udc.fixtures.EmpleadoPorts;

public class CreateEmpleadoTemporalService implements CreateEmpleadoTemporalUseCase {

    private final SaveEmpleadoTemporalPort savePort = EmpleadoPorts.saveEmpleadoTemporalPort;

    @Override
    public EmpleadoTemporal execute(CreateEmpleadoTemporalCommand command) {
        if (command.salarioMensualFijo() <= 0) {
            throw new IllegalArgumentException("El salario mensual fijo debe ser mayor a cero.");
        }
        if (command.duracionContrato() <= 0) {
            throw new IllegalArgumentException("La duración del contrato debe ser mayor a cero.");
        }

        EmpleadoTemporal empleado = new EmpleadoTemporal(
                command.nombre(),
                command.apellidos(),
                command.duracionContrato(),
                command.tipoDocumento(),
                command.documento(),
                command.salarioMensualFijo()
        );
        return savePort.create(empleado);
    }
}