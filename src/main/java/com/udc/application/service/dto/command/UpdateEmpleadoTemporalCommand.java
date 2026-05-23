package com.udc.application.service.dto.command;

import com.udc.domain.enums.empleado.tipoDocumento;
import com.udc.domain.models.ARL.ARL;

public record UpdateEmpleadoTemporalCommand(
        String id,
        String nombre,
        String apellidos,
        tipoDocumento tipoDocumento,
        String documento,
        ARL arl,
        int duracionContrato,
        double salarioMensualFijo
) {
}
