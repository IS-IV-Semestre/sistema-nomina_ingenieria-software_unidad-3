package com.udc.domain.models.empleado;

import com.udc.domain.enums.empleado.tipoDocumento;
import com.udc.domain.exceptions.empleado.InvalidHorasTrabajadas;
import com.udc.domain.valueobjects.empleado.TarifaHora;

public class EmpleadoPorHoras extends Empleado {

    private static final double HORAS_REGULARES_MAX = 40.0;
    private static final double MULTIPLICADOR_HORAS_EXTRAS = 1.5;
    private static final int ANIOS_MINIMOS_FONDO_AHORRO = 1;
    private static final double PORCENTAJE_FONDO_AHORRO = 0.02;

    private final TarifaHora tarifaHora;
    private final double horasTrabajadas;
    private final boolean aceptaFondoAhorro;

    public EmpleadoPorHoras(String nombre, String apellido, int antiguedadAnios,
                             tipoDocumento tipoDocumento, String documento,
                             double tarifaHora, double horasTrabajadas,
                             boolean aceptaFondoAhorro) {
        super(nombre, apellido, antiguedadAnios, tipoDocumento, documento);

        if (horasTrabajadas < 0) {
            throw InvalidHorasTrabajadas.becauseIsNegative();
        }
        if (horasTrabajadas == 0) {
            throw InvalidHorasTrabajadas.becauseIsZero();
        }

        this.tarifaHora = new TarifaHora(tarifaHora);
        this.horasTrabajadas = horasTrabajadas;
        this.aceptaFondoAhorro = aceptaFondoAhorro;
    }

    /**
     * Las primeras 40 horas se pagan a tarifa normal; las adicionales a 1.5x.
     */
    @Override
    public double calcularSalarioBruto() {
        if (horasTrabajadas <= HORAS_REGULARES_MAX) {
            return horasTrabajadas * tarifaHora.value();
        }
        double pagoRegular = HORAS_REGULARES_MAX * tarifaHora.value();
        double horasExtras = horasTrabajadas - HORAS_REGULARES_MAX;
        double pagoExtras = horasExtras * tarifaHora.value() * MULTIPLICADOR_HORAS_EXTRAS;
        return pagoRegular + pagoExtras;
    }

    /**
     * Fondo de ahorro del 2% si tiene más de 1 año y acepta el beneficio. No recibe bonos.
     */
    @Override
    public double calcularBeneficios() {
        boolean elegibleFondoAhorro = getAntiguedadAnios() > ANIOS_MINIMOS_FONDO_AHORRO && aceptaFondoAhorro;
        return elegibleFondoAhorro ? calcularSalarioBruto() * PORCENTAJE_FONDO_AHORRO : 0;
    }

    public double getTarifaHora() {
        return tarifaHora.value();
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public boolean isAceptaFondoAhorro() {
        return aceptaFondoAhorro;
    }
}
