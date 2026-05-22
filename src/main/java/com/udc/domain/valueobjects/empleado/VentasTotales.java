package com.udc.domain.valueobjects.empleado;

import com.udc.domain.exceptions.empleado.InvalidVentasTotales;

public record VentasTotales(double value) {

    public VentasTotales(double value) {
        if (value < 0) {
            throw InvalidVentasTotales.becauseIsNegative();
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}