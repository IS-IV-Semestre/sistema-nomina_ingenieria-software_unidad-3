package com.udc.domain.valueobjects.empleado;

import com.udc.domain.exceptions.empleado.InvalidTarifaHora;

public record TarifaHora(double value) {

    public TarifaHora(double value) {
        if (value < 0) {
            throw InvalidTarifaHora.becauseIsNegative();
        }
        if (value == 0) {
            throw InvalidTarifaHora.becauseIsZero();
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
