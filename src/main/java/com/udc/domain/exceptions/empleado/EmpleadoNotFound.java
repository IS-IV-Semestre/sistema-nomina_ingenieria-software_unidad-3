package com.udc.domain.exceptions.empleado;

import com.udc.domain.exceptions.DomainException;

public class EmpleadoNotFound extends DomainException {
    public static EmpleadoNotFound becauseId(String id) {
        return new EmpleadoNotFound("El empleado con el id " + id + " no existe");
    }
    private EmpleadoNotFound(String message) {
        super(message);
    }
}
