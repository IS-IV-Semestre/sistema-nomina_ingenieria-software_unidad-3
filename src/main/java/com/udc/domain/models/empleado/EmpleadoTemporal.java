package com.udc.domain.models.empleado;

import com.udc.domain.enums.empleado.tipoDocumento;

public class EmpleadoTemporal extends Empleado {
    protected int duracionContrato; 
    protected double salarioMensualFijo;

    public EmpleadoTemporal(String nombre, String apellido, int duracionContrato, tipoDocumento tipoDoc, String documento, 
        double salarioMensualFijo) {
            super(nombre, apellido, duracionContrato, tipoDoc, documento);
            if (duracionContrato <= 0) {
                throw new IllegalArgumentException("La duración del contrato debe ser mayor a cero.");
            }
            if (salarioMensualFijo <= 0) {
                throw new IllegalArgumentException("El salario mensual debe ser mayor a cero.");
            }
            this.duracionContrato = duracionContrato;
            this.salarioMensualFijo = salarioMensualFijo;
            
    }

    @Override
    public double calcularBeneficios() {
        return 0;
    } 
    @Override
    public double calcularSalarioBruto() {
        return salarioMensualFijo;
    }
    public double getDuracionContrato() {
        return duracionContrato;
    }
    public double getSalarioMensualFijo() {
        return salarioMensualFijo;
    }
}
