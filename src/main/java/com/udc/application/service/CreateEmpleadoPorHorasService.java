package com.udc.application.service;

import com.udc.application.port.in.CreateEmpleadoPorHorasUseCase;
import com.udc.application.port.out.SaveEmpleadoPorHorasPort;
import com.udc.application.service.dto.command.CreateEmpleadoPorHorasCommand;
import com.udc.domain.models.empleado.EmpleadoPorHoras;
import com.udc.fixtures.EmpleadoPorts;

public class CreateEmpleadoPorHorasService implements CreateEmpleadoPorHorasUseCase {

    private final SaveEmpleadoPorHorasPort savePort = EmpleadoPorts.saveEmpleadoPorHorasPort;

    @Override
    public EmpleadoPorHoras execute(CreateEmpleadoPorHorasCommand command) {
        if (command.tarifaHora() <= 0) {
            throw new IllegalArgumentException("La tarifa por hora debe ser mayor a cero.");
        }
        if (command.horasTrabajadas() <= 0) {
            throw new IllegalArgumentException("Las horas trabajadas deben ser mayores a cero.");
        }

        EmpleadoPorHoras empleado = new EmpleadoPorHoras(
                command.nombre(),
                command.apellidos(),
                command.antiguedadAnios(),
                command.tipoDocumento(),
                command.documento(),
                command.tarifaHora(),
                command.horasTrabajadas(),
                command.aceptaFondoAhorro()
        );

        return savePort.create(empleado);
    }
}
