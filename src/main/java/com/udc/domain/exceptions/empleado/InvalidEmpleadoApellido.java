package com.udc.domain.exceptions.empleado;

import com.udc.domain.exceptions.DomainException;

public final class InvalidEmpleadoApellido extends DomainException {

    private static final int MAX_LENGTH = 50;
    private static final int MIN_LENGTH = 3;
    private static final String BLANK_MESSAGE = "El nombre del empleado no puede estar vacio";
    private static final String TOO_LONG_MESSAGE = "El nombre del empleado no puede tener mas de " + MAX_LENGTH + " caracteres";
    private static final String INVALID_FORMAT_ONLY_LETTERS = "El nombre del empleado debe tener solo letras";
    private static final String TOO_SHORT_MESSAGE = "El nombre del empleado debe tener al menos " + MIN_LENGTH + " caracteres";

    private InvalidEmpleadoApellido(String message) {
        super(message);
    }

    public static InvalidEmpleadoApellido becauseIsEmpty() {
        return new InvalidEmpleadoApellido(BLANK_MESSAGE);
    }

    public static InvalidEmpleadoApellido becauseIsTooLong() {
        return new InvalidEmpleadoApellido(TOO_LONG_MESSAGE);
    }

    public static InvalidEmpleadoApellido becauseIsTooShort() {
        return new InvalidEmpleadoApellido(TOO_SHORT_MESSAGE);
    }

    public static InvalidEmpleadoApellido becauseHaveInvalidFormat() {
        return new InvalidEmpleadoApellido(INVALID_FORMAT_ONLY_LETTERS);
    }
}
