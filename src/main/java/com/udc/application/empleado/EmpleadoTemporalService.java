package com.udc.application.empleado;

import com.udc.domain.enums.empleado.tipoDocumento;
import com.udc.domain.models.empleado.EmpleadoTemporal;

public class EmpleadoTemporalService {
    public EmpleadoTemporalService() {}

    public EmpleadoTemporal crearEmpleadoTemporal(String nombre, String apellido, int duracionContrato, tipoDocumento tipoDoc,
                                         String documento, double salarioMensualFijo) {
        return new EmpleadoTemporal(nombre, apellido, duracionContrato, tipoDoc, documento, salarioMensualFijo);
    }

    public double obtenerSalarioBruto(EmpleadoTemporal e) {
        return e.calcularSalarioBruto();
    }

    public double obtenerBeneficios(EmpleadoTemporal e) {
        return e.calcularBeneficios();
    }    
}
    