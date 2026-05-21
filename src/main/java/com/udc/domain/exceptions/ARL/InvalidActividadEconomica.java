package com.udc.domain.exceptions.ARL;

import com.udc.domain.exceptions.DomainException;

public class InvalidActividadEconomica extends DomainException {
    private static final String BLANK_MESSAGE = "La actividad economica no puede estar vacia";

    private InvalidActividadEconomica(String message) {
        super(message);
    }

    public static InvalidActividadEconomica becauseIsEmpty() {
        return new InvalidActividadEconomica(BLANK_MESSAGE);
    }
}
