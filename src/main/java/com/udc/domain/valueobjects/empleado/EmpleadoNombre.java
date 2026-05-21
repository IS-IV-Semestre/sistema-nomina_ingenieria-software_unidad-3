package com.udc.domain.valueobjects.empleado;

import com.udc.domain.exceptions.empleado.InvalidEmpleadoNombre;

public record EmpleadoNombre(String value) {
    public EmpleadoNombre(String value) {
        String normilizedValue = value.trim();

        // Comprobar que no este vacio
        if(normilizedValue.isBlank() || normilizedValue.isBlank()){
            throw InvalidEmpleadoNombre.becauseIsEmpty();
        }

        if(normilizedValue.length() > 50){
            throw InvalidEmpleadoNombre.becauseIsTooLong();
        }

        if(normilizedValue.length() < 3){
            throw InvalidEmpleadoNombre.becauseIsTooShort();
        }

        if(!normilizedValue.matches("[A-Za-z]+")){
            throw InvalidEmpleadoNombre.becauseHaveInvalidFormat();
        }

        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
