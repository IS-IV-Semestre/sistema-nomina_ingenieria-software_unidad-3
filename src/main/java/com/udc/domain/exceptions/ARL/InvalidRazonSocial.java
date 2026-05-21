package com.udc.domain.exceptions.ARL;

import com.udc.domain.exceptions.DomainException;

public final class InvalidRazonSocial extends DomainException {

    private static final String BLANK_MESSAGE = "La razon social no puede estar vacia";

    private InvalidRazonSocial(String message) {
        super(message);
    }

    public static InvalidRazonSocial becauseIsEmpty() {
        return new InvalidRazonSocial(BLANK_MESSAGE);
    }
}
