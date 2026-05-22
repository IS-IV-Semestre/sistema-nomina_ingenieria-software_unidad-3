package com.udc.domain.models.empleado;

import com.udc.domain.enums.empleado.tipoDocumento;
import com.udc.domain.valueobjects.empleado.SalarioBase;
import com.udc.domain.valueobjects.empleado.VentasTotales;

public class EmpleadoPorComision extends Empleado {

    private static final double LIMITE_BONO_VENTAS = 20_000_000.0;
    private static final double PORCENTAJE_BONO_VENTAS = 0.03;
    private static final double BONO_ALIMENTACION = 1_000_000.0;

    private final SalarioBase salarioBase;
    private final double porcentajeComision;
    private final VentasTotales ventasTotales;

    /**
     * @param porcentajeComision porcentaje expresado en decimal (ej. 0.15 = 15%)
     */
    public EmpleadoPorComision(String nombre, String apellido, int antiguedadAnios,
                                tipoDocumento tipoDocumento, String documento,
                                double salarioBase, double porcentajeComision,
                                double ventasTotales) {
        super(nombre, apellido, antiguedadAnios, tipoDocumento, documento);

        if (porcentajeComision <= 0 || porcentajeComision > 1) {
            throw new IllegalArgumentException("El porcentaje de comisión debe estar entre 0 y 1 (exclusivo)");
        }

        this.salarioBase = new SalarioBase(salarioBase);
        this.porcentajeComision = porcentajeComision;
        this.ventasTotales = new VentasTotales(ventasTotales);
    }

    /**
     * Salario bruto = salario base + comisión sobre ventas totales.
     */
    @Override
    public double calcularSalarioBruto() {
        return salarioBase.value() + (ventasTotales.value() * porcentajeComision);
    }

    /**
     * Beneficios: bono del 3% sobre ventas si superan $20.000.000 + bono alimentación (empleado permanente).
     */
    @Override
    public double calcularBeneficios() {
        double bonoVentas = ventasTotales.value() > LIMITE_BONO_VENTAS
                ? ventasTotales.value() * PORCENTAJE_BONO_VENTAS
                : 0;
        return bonoVentas + BONO_ALIMENTACION;
    }

    public double getSalarioBase() {
        return salarioBase.value();
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public double getVentasTotales() {
        return ventasTotales.value();
    }
}