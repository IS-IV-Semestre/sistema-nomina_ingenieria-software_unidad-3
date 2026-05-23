package com.udc.application.empleado;

import com.udc.domain.enums.empleado.tipoDocumento;
import com.udc.domain.models.empleado.EmpleadoPorHoras;

public class EmpleadoPorHorasService {

    public EmpleadoPorHorasService() {}

    public EmpleadoPorHoras crearEmpleado(String nombre, String apellido, int antiguedadAnios,
                                          tipoDocumento tipoDoc, String documento,
                                          double tarifaHora, double horasTrabajadas,
                                          boolean aceptaFondoAhorro) {
        return new EmpleadoPorHoras(nombre, apellido, antiguedadAnios, tipoDoc, documento,
                tarifaHora, horasTrabajadas, aceptaFondoAhorro);
    }

    public double obtenerSalarioBruto(EmpleadoPorHoras e) {
        return e.calcularSalarioBruto();
    }

    public double obtenerBeneficios(EmpleadoPorHoras e) {
        return e.calcularBeneficios();
    }

    public double obtenerDeducciones(EmpleadoPorHoras e) {
        return e.calcularDeducciones();
    }

    public double obtenerSalarioNeto(EmpleadoPorHoras e) {
        return e.calcularSalarioNeto();
    }
}
