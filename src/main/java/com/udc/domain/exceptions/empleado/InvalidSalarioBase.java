package com.udc.domain.exceptions.empleado;

import com.udc.domain.exceptions.DomainException;

public final class InvalidSalarioBase extends DomainException {

    private static final String NEGATIVE_MESSAGE = "El salario base no puede ser negativo";
    private static final String ZERO_MESSAGE = "El salario base debe ser mayor a cero";

    private InvalidSalarioBase(String message) {
        super(message);
    }

    public static InvalidSalarioBase becauseIsNegative() {
        return new InvalidSalarioBase(NEGATIVE_MESSAGE);
    }

    public static InvalidSalarioBase becauseIsZero() {
        return new InvalidSalarioBase(ZERO_MESSAGE);
    }
}