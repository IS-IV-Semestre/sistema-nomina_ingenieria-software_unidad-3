package com.udc.domain.valueobjects.empleado;

public record EmpleadoDocumento(String value) {
    public EmpleadoDocumento(String value) {
        String normilizedValue = value.trim();
        if(normilizedValue.isBlank() || normilizedValue.isBlank()){
            throw new IllegalArgumentException("El valor del documento no puede estar vacio");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
