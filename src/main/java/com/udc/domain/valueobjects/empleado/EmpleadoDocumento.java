package com.udc.domain.valueobjects.empleado;

import com.udc.domain.exceptions.empleado.InvalidEmpleadoDocumento;

public record EmpleadoDocumento(String value) {
    public EmpleadoDocumento(String value) {
        String normilizedValue = value.trim();

        // Comprobar que no este vacio
        if(normilizedValue.isBlank() || normilizedValue.isBlank()){
            throw InvalidEmpleadoDocumento.becauseIsEmpty();
        }

        if(normilizedValue.length() > 10){
            throw InvalidEmpleadoDocumento.becauseIsTooLong();
        }

        if(normilizedValue.length() < 8){
            throw InvalidEmpleadoDocumento.becauseIsTooShort();
        }

        if(!normilizedValue.matches("[0-9]+")){
            throw InvalidEmpleadoDocumento.becauseHaveInvalidFormatOnlyNumbers();
        }

        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
