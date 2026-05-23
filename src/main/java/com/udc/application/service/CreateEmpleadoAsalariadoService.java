package com.udc.application.service;

import com.udc.application.port.in.CreateEmpleadoAsalariadoUseCase;
import com.udc.application.port.out.SaveEmpleadoAsalariadoPort;
import com.udc.application.service.dto.command.CreateEmpleadoAsalariadoCommand;
import com.udc.domain.exceptions.empleado.InvalidSalarioBase;
import com.udc.domain.models.empleado.EmpleadoAsalariado;
import com.udc.fixtures.EmpleadoPorts;

public class CreateEmpleadoAsalariadoService implements CreateEmpleadoAsalariadoUseCase {

    private final SaveEmpleadoAsalariadoPort savePort = EmpleadoPorts.saveEmpleadoAsalariadoPort;



    @Override
    public EmpleadoAsalariado execute(CreateEmpleadoAsalariadoCommand command) {
        final EmpleadoAsalariado empleadoToSave = new EmpleadoAsalariado(
                    command.id(),
                    command.apellidos(),
                    command.antiguedadAnios(),
                    command.tipoDocumento(),
                    command.documento(),
                    command.salarioMensual()
        );

        // Realizar validaciones necesarias antes de guardar el empleado
        if (empleadoToSave.getSalarioMensual() == 0) {
            throw InvalidSalarioBase.becauseIsZero();
        }

        if (empleadoToSave.getSalarioMensual() < 0) {
            throw InvalidSalarioBase.becauseIsNegative();
        }

        return savePort.create(empleadoToSave); // Guardar el empleado en la lista de empleados asalariados y la lista de empleados

    }
}
