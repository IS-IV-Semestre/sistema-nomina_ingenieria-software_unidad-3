package com.udc.domain.exceptions.empleado;

import com.udc.domain.exceptions.DomainException;

public final class InvalidEmpleadoDocumento extends DomainException {
    private static final String BLANK_MESSAGE = "El documento del empleado no puede estar vacio";
    private static final String TOO_LEN_MESSAGE = "El documento del empleado debe tener 10 caracteres";
    private static final String INVALID_FORMAT_ONLY_NUMBERS = "El documento del empleado debe tener solo numeros";

    private InvalidEmpleadoDocumento(String message) {
        super(message);
    }

    public static InvalidEmpleadoDocumento becauseIsEmpty() {
        return new InvalidEmpleadoDocumento(BLANK_MESSAGE);
    }
    public static InvalidEmpleadoDocumento becauseIsTooLong() {
        return new InvalidEmpleadoDocumento(TOO_LEN_MESSAGE);
    }
    public static InvalidEmpleadoDocumento becauseIsTooShort() {
        return new InvalidEmpleadoDocumento(TOO_LEN_MESSAGE);
    }

    public static InvalidEmpleadoDocumento becauseHaveInvalidFormatOnlyNumbers() {
        return new InvalidEmpleadoDocumento(INVALID_FORMAT_ONLY_NUMBERS);
    }
}
