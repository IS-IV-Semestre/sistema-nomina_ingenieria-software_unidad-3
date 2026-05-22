package com.udc.application.empleado;

import com.udc.domain.enums.empleado.tipoDocumento;
import com.udc.domain.models.empleado.EmpleadoAsalariado;

/*
 Servicio (caso de uso) para operaciones de negocio sobre EmpleadoAsalariado.
*/
public class EmpleadoAsalariadoService {

    public EmpleadoAsalariadoService() {}

    public EmpleadoAsalariado crearEmpleado(String nombre, String apellido, int antiguedadAnios,
                                             tipoDocumento tipoDoc, String documento, double salarioMensual) {
        return new EmpleadoAsalariado(nombre, apellido, antiguedadAnios, tipoDoc, documento, salarioMensual);
    }

    public double obtenerSalarioBruto(EmpleadoAsalariado e) {
        return e.calcularSalarioBruto();
    }

    public double obtenerBeneficios(EmpleadoAsalariado e) {
        return e.calcularBeneficios();
    }

    public double obtenerDeducciones(EmpleadoAsalariado e) {
        return e.calcularDeducciones();
    }

    public double obtenerSalarioNeto(EmpleadoAsalariado e) {
        return e.calcularSalarioNeto();
    }
}

