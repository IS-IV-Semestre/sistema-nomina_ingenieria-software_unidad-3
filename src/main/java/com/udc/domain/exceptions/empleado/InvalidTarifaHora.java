package com.udc.domain.exceptions.empleado;

import com.udc.domain.exceptions.DomainException;

public final class InvalidTarifaHora extends DomainException {

    private static final String NEGATIVE_MESSAGE = "La tarifa por hora no puede ser negativa";
    private static final String ZERO_MESSAGE = "La tarifa por hora debe ser mayor a cero";

    private InvalidTarifaHora(String message) {
        super(message);
    }

    public static InvalidTarifaHora becauseIsNegative() {
        return new InvalidTarifaHora(NEGATIVE_MESSAGE);
    }

    public static InvalidTarifaHora becauseIsZero() {
        return new InvalidTarifaHora(ZERO_MESSAGE);
    }
}
