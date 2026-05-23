package com.udc.application.service.dto.command;

import com.udc.domain.enums.empleado.tipoDocumento;

public record CreateEmpleadoTemporalCommand(
        String nombre,
        String apellidos,
        tipoDocumento tipoDocumento,
        String documento,
        int duracionContrato,
        double salarioMensualFijo
) {
}
