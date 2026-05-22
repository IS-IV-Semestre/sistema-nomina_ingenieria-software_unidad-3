package com.udc.domain.models.empleado;

import com.udc.domain.enums.empleado.tipoDocumento;

public class EmpleadoAsalariado extends Empleado{

    private double salarioMensual;

    public EmpleadoAsalariado(String nombre, String apellido, int antiguedadAnios, tipoDocumento tipoDocumento, String documento, double salarioMensual) {
        super(nombre, apellido, antiguedadAnios, tipoDocumento, documento);
        if (salarioMensual < 0) {
            throw new IllegalArgumentException("El salario mensual no puede ser negativo.");
        }
        this.salarioMensual = salarioMensual;
    }

    @Override
    public double calcularSalarioBruto() {
        return salarioMensual;
    }

    @Override
    public double calcularBeneficios() {
        double beneficios = 0.0;
        // Bono mensual del 10% si lleva más de 5 años
        if (getAntiguedadAnios() > 5) {
            beneficios += salarioMensual * 0.10;
        }
        // Bono de alimentación para empleados permanentes (Asalariado)
        beneficios += 1_000_000.0;
        return beneficios;
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }

}
