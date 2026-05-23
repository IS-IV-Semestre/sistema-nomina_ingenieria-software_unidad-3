package com.udc.application.service.dto.command;

import com.udc.domain.enums.empleado.tipoDocumento;
import com.udc.domain.models.ARL.ARL;

public record UpdateEmpleadoAsalariadoCommand(
        String id,
        String nombre,
        String apellidos,
        tipoDocumento tipoDocumento,
        String documento,
        int antiguedadAnios,
        ARL arl,
        double salarioMensual
) {
}
