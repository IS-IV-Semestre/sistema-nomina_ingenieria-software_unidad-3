package com.udc.application.service.mapper;

import com.udc.application.service.dto.command.CreateEmpleadoAsalariadoCommand;
import com.udc.application.service.dto.command.DeleteEmpleadoCommand;
import com.udc.application.service.dto.command.UpdateEmpleadoAsalariadoCommand;
import com.udc.application.service.dto.query.GetEmpleadoByIdQuery;
import com.udc.domain.models.empleado.Empleado;
import com.udc.domain.models.empleado.EmpleadoAsalariado;
import com.udc.domain.valueobjects.empleado.EmpleadoId;

public class EmpleadoAsalariadoMapper {

    public Empleado fromCreateCommandToModel(CreateEmpleadoAsalariadoCommand command){
        return new EmpleadoAsalariado(
                command.nombre(),
                command.apellidos(),
                command.antiguedadAnios(),
                command.tipoDocumento(),
                command.documento(),
                command.salarioMensual()
        );
    }

    public EmpleadoAsalariado fromUpdateCommandToModel(final UpdateEmpleadoAsalariadoCommand command){
        return new EmpleadoAsalariado(
                command.id(),
                command.apellidos(),
                command.antiguedadAnios(),
                command.tipoDocumento(),
                command.documento(),
                command.salarioMensual()
        );
    }

    public EmpleadoId fromGetEmpleadoByIdQueryToEmpleadoId(final GetEmpleadoByIdQuery query){
        return new EmpleadoId(
                query.id()
        );
    }

    public EmpleadoId fromDeleteCommandToUserId(final DeleteEmpleadoCommand command){
        return new EmpleadoId(
                command.id()
        );
    }
}
