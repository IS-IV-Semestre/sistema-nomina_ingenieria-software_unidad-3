package com.udc.domain.valueobjects.empleado;

public record EmpleadoId(String value){
    public EmpleadoId(String value) {
        String normilizedValue = value.trim();
        if(normilizedValue.isBlank() || normilizedValue.isBlank()){
            throw new IllegalArgumentException("El valor de empleado id no puede estar vacio");
        }else{
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
