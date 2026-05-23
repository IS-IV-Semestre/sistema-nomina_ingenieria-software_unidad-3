package com.udc.application.service;

import com.udc.application.port.in.UpdateEmpleadoPorComisionUseCase;
import com.udc.application.port.out.UpdateEmpleadoPorComisionPort;
import com.udc.application.service.dto.command.UpdateEmpleadoPorComisionCommand;
import com.udc.domain.models.empleado.EmpleadoPorComision;
import com.udc.fixtures.EmpleadoPorts;

public class UpdateEmpleadoPorComisionService implements UpdateEmpleadoPorComisionUseCase {

    private final UpdateEmpleadoPorComisionPort updatePort = EmpleadoPorts.updateEmpleadoPorComisionPort;

    @Override
    public void execute(UpdateEmpleadoPorComisionCommand command) {
        if (EmpleadoPorts.getAllEmpleadosPort.execute().isEmpty()) {
            throw new RuntimeException("No hay empleados registrados para actualizar.");
        }

        if (command.id() == null) {
            throw new IllegalArgumentException("El ID del empleado no puede ser nulo.");
        }

        if (command.salarioBase() <= 0) {
            throw new IllegalArgumentException("El salario base debe ser mayor a cero.");
        }

        if (command.porcentajeComision() <= 0 || command.porcentajeComision() > 1) {
            throw new IllegalArgumentException("El porcentaje de comisión debe estar entre 0 y 1 (exclusivo).");
        }

        if (command.ventasTotales() < 0) {
            throw new IllegalArgumentException("Las ventas totales no pueden ser negativas.");
        }

        EmpleadoPorComision empleadoActualizado = new EmpleadoPorComision(
                command.nombre(),
                command.apellidos(),
                command.antiguedadAnios(),
                command.tipoDocumento(),
                command.documento(),
                command.salarioBase(),
                command.porcentajeComision(),
                command.ventasTotales()
        );

        updatePort.update(empleadoActualizado);
    }
}
