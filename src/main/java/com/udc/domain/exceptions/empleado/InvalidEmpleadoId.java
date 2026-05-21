package com.udc.domain.exceptions.empleado;

import com.udc.domain.exceptions.DomainException;

public final class InvalidEmpleadoId extends DomainException {
    private static final String BLANK_MESSAGE = "El id del empleado no puede estar vacio";

    private InvalidEmpleadoId(String message) {
        super(message);
    }

    public static InvalidEmpleadoId becauseIsEmpty() {
        return new InvalidEmpleadoId(BLANK_MESSAGE);
    }
}
