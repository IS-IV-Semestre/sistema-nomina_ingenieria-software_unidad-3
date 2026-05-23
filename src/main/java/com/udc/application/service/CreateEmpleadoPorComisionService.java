package com.udc.application.service;

import com.udc.application.port.in.CreateEmpleadoPorComisionUseCase;
import com.udc.application.port.out.SaveEmpleadoPorComisionPort;
import com.udc.application.service.dto.command.CreateEmpleadoPorComisionCommand;
import com.udc.domain.models.empleado.EmpleadoPorComision;
import com.udc.fixtures.EmpleadoPorts;

public class CreateEmpleadoPorComisionService implements CreateEmpleadoPorComisionUseCase {

    private final SaveEmpleadoPorComisionPort savePort = EmpleadoPorts.saveEmpleadoPorComisionPort;

    @Override
    public EmpleadoPorComision execute(CreateEmpleadoPorComisionCommand command) {
        if (command.salarioBase() <= 0) {
            throw new IllegalArgumentException("El salario base debe ser mayor a cero.");
        }
        if (command.porcentajeComision() <= 0 || command.porcentajeComision() > 1) {
            throw new IllegalArgumentException("El porcentaje de comisión debe estar entre 0 y 1 (exclusivo).");
        }
        if (command.ventasTotales() < 0) {
            throw new IllegalArgumentException("Las ventas totales no pueden ser negativas.");
        }

        EmpleadoPorComision empleado = new EmpleadoPorComision(
                command.nombre(),
                command.apellidos(),
                command.antiguedadAnios(),
                command.tipoDocumento(),
                command.documento(),
                command.salarioBase(),
                command.porcentajeComision(),
                command.ventasTotales()
        );

        return savePort.create(empleado);
    }
}
