package com.udc.domain.models.empleado;

import java.sql.Timestamp;

// Modelo de datos para las horas trabajadas por un empleado
public record HoraTrabajada(Timestamp horaInicio, Timestamp horaFin,String idEmpleado, double horasTrabajadas) {

    public HoraTrabajada(Timestamp inico, Timestamp fin,String idEmpleado) {
        // Modificamos el constructor para calcular las horas trabajadas
        this(inico, fin, idEmpleado, (double) ((fin.getTime() - inico.getTime()) / 1000 / 60 / 60));
    }
}
