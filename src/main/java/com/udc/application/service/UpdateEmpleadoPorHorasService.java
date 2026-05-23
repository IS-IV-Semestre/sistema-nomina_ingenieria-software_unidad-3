package com.udc.application.service;

import com.udc.application.port.in.UpdateEmpleadoPorHorasUseCase;
import com.udc.application.port.out.UpdateEmpleadoPorHorasPort;
import com.udc.application.service.dto.command.UpdateEmpleadoPorHorasCommand;
import com.udc.domain.models.empleado.EmpleadoPorHoras;
import com.udc.fixtures.EmpleadoPorts;

public class UpdateEmpleadoPorHorasService implements UpdateEmpleadoPorHorasUseCase {

    private final UpdateEmpleadoPorHorasPort updatePort = EmpleadoPorts.updateEmpleadoPorHorasPort;

    @Override
    public void execute(UpdateEmpleadoPorHorasCommand command) {
        if (EmpleadoPorts.getAllEmpleadosPort.execute().isEmpty()) {
            throw new RuntimeException("No hay empleados registrados para actualizar.");
        }

        if (command.id() == null) {
            throw new IllegalArgumentException("El ID del empleado no puede ser nulo.");
        }

        if (command.tarifaHora() <= 0) {
            throw new IllegalArgumentException("La tarifa por hora debe ser mayor a cero.");
        }

        if (command.horasTrabajadas() <= 0) {
            throw new IllegalArgumentException("Las horas trabajadas deben ser mayores a cero.");
        }

        EmpleadoPorHoras empleadoActualizado = new EmpleadoPorHoras(
                command.nombre(),
                command.apellidos(),
                command.antiguedadAnios(),
                command.tipoDocumento(),
                command.documento(),
                command.tarifaHora(),
                command.horasTrabajadas(),
                command.aceptaFondoAhorro()
        );

        updatePort.update(empleadoActualizado);
    }
}
