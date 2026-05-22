package com.udc.domain.valueobjects.empleado;

import com.udc.domain.exceptions.empleado.InvalidSalarioBase;

public record SalarioBase(double value) {

    public SalarioBase(double value) {
        if (value < 0) {
            throw InvalidSalarioBase.becauseIsNegative();
        }
        if (value == 0) {
            throw InvalidSalarioBase.becauseIsZero();
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}