package com.udc.domain.exceptions.empleado;

import com.udc.domain.exceptions.DomainException;

public final class InvalidEmpleadoNombre extends DomainException {

    private static final int MAX_LENGTH = 50;
    private static final int MIN_LENGTH = 3;
    private static final String BLANK_MESSAGE = "El nombre del empleado no puede estar vacio";
    private static final String TOO_LONG_MESSAGE = "El nombre del empleado no puede tener mas de " + MAX_LENGTH + " caracteres";
    private static final String INVALID_FORMAT_ONLY_LETTERS = "El nombre del empleado debe tener solo letras";
    private static final String TOO_SHORT_MESSAGE = "El nombre del empleado debe tener al menos " + MIN_LENGTH + " caracteres";

    private InvalidEmpleadoNombre(String message) {
        super(message);
    }

    public static InvalidEmpleadoNombre becauseIsEmpty() {
        return new InvalidEmpleadoNombre(BLANK_MESSAGE);
    }

    public static InvalidEmpleadoNombre becauseIsTooLong() {
        return new InvalidEmpleadoNombre(TOO_LONG_MESSAGE);
    }

    public static InvalidEmpleadoNombre becauseIsTooShort() {
        return new InvalidEmpleadoNombre(TOO_SHORT_MESSAGE);
    }

    public static InvalidEmpleadoNombre becauseHaveInvalidFormat() {
        return new InvalidEmpleadoNombre(INVALID_FORMAT_ONLY_LETTERS);
    }

}
