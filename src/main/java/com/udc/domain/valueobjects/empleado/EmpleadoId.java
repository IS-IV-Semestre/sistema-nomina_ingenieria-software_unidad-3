package com.udc.domain.valueobjects.empleado;

import com.udc.domain.exceptions.empleado.InvalidEmpleadoDocumento;
import com.udc.domain.exceptions.empleado.InvalidEmpleadoId;

public record EmpleadoId(String value){
    public EmpleadoId(String value) {
        String normilizedValue = value.trim();

        // Comprobar que no este vacio
        if(normilizedValue.isBlank() || normilizedValue.isBlank()){
            throw InvalidEmpleadoId.becauseIsEmpty();
        }

        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
