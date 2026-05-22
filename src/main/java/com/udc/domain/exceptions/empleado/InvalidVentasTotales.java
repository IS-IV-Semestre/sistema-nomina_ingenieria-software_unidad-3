package com.udc.domain.exceptions.empleado;

import com.udc.domain.exceptions.DomainException;

public final class InvalidVentasTotales extends DomainException {

    private static final String NEGATIVE_MESSAGE = "Las ventas de un empleado por comisión no pueden ser menores a $0";

    private InvalidVentasTotales(String message) {
        super(message);
    }

    public static InvalidVentasTotales becauseIsNegative() {
        return new InvalidVentasTotales(NEGATIVE_MESSAGE);
    }
}