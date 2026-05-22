package com.udc.application.service.dto.command;

import com.udc.domain.enums.empleado.tipoDocumento;

public record CreateEmpleadoAsalariadoCommand(
        String id,
        String nombre,
        String apellidos,
        tipoDocumento tipoDocumento,
        String documento,
        int antiguedadAnios,
        double salarioMensual
) {
}
