package com.udc.domain.valueobjects.empleado;

import com.udc.domain.exceptions.empleado.InvalidEmpleadoApellido;

public record EmpleadoApellido(String value) {
    public EmpleadoApellido(String value) {
        String normilizedValue = value.trim();

        // Comprobar que no este vacio
        if(normilizedValue.isBlank() || normilizedValue.isBlank()){
            throw InvalidEmpleadoApellido.becauseIsEmpty();
        }

        // Comprobar que no sea demasiado corto
        if(normilizedValue.length() < 3){
            throw InvalidEmpleadoApellido.becauseIsTooShort();
        }

        // Comprobar que no sea demasiado largo
        if(normilizedValue.length() > 50){
            throw InvalidEmpleadoApellido.becauseIsTooLong();
        }

        // Comprobar que solo tenga letras
        if(!normilizedValue.matches("[A-Za-z]+")){
            throw InvalidEmpleadoApellido.becauseHaveInvalidFormat();
        }

        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
