package com.udc.domain.valueobjects.empleado;

public record EmpleadoApellido(String value) {
    public EmpleadoApellido(String value) {
        String normilizedValue = value.trim();

        if(normilizedValue.isBlank() || normilizedValue.isBlank()){
            throw new IllegalArgumentException("El apellido del empleado no puede estar vacio");
        }

        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
