package com.udc.domain.exceptions.ARL;

import com.udc.domain.exceptions.DomainException;

public final class InvalidARLNit extends DomainException {

    private static final String BLANK_MESSAGE = "El nit no puede estar vacio";
    private static final String INVALID_FORMAT_ONLY_NUMBERS = "El nit debe tener solo numeros";
    private static final String TOO_LEN_MESSAGE = "El nit debe tener 10 caracteres";
    private static final String INVALID_FORMAT_PATTERN = "Formato invalido, por favor ingrese un nit valido";

    private InvalidARLNit(String message) {
        super(message);
    }

    public static InvalidARLNit becauseIsEmpty() {
        return new InvalidARLNit(BLANK_MESSAGE);
    }

    public static InvalidARLNit becauseNotHaveOnlyNumbers() {
        return new InvalidARLNit(INVALID_FORMAT_ONLY_NUMBERS);
    }

    public static InvalidARLNit becauseIsNot10Characters() {
        return new InvalidARLNit(TOO_LEN_MESSAGE);
    }

    public static InvalidARLNit becauseIsInvalidFormat() {
        return new InvalidARLNit(INVALID_FORMAT_PATTERN);
    }
}
