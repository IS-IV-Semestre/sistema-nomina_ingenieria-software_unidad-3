package com.udc.domain.exceptions.empleado;

import com.udc.domain.exceptions.DomainException;

public final class InvalidHorasTrabajadas extends DomainException {

    private static final String NEGATIVE_MESSAGE = "Las horas trabajadas no pueden ser negativas";
    private static final String ZERO_MESSAGE = "Las horas trabajadas deben ser mayores a cero";

    private InvalidHorasTrabajadas(String message) {
        super(message);
    }

    public static InvalidHorasTrabajadas becauseIsNegative() {
        return new InvalidHorasTrabajadas(NEGATIVE_MESSAGE);
    }

    public static InvalidHorasTrabajadas becauseIsZero() {
        return new InvalidHorasTrabajadas(ZERO_MESSAGE);
    }
}
