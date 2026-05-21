package com.udc.domain.valueobjects.empleado;

public record EmpleadoNombre(String value) {
    public EmpleadoNombre(String value) {
        String normilizedValue = value.trim();

        if(normilizedValue.isBlank() || normilizedValue.isBlank()){
            throw new IllegalArgumentException("El nombre del empleado no puede estar vacio");
        }

        this.value = value;
    }
}
