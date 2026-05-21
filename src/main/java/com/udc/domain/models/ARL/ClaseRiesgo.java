package com.udc.domain.models.ARL;

public enum ClaseRiesgo {
    MINIMO("Riesgo I - Mínimo", 0.00522),   // 0.522%
    BAJO("Riesgo II - Bajo", 0.01044),     // 1.044%
    MEDIO("Riesgo III - Medio", 0.02436),   // 2.436%
    ALTO("Riesgo IV - Alto", 0.04350),     // 4.350%
    MAXIMO("Riesgo V - Máximo", 0.08700);  // 8.700%

    private final String descripcion;
    private final double tarifaPorcentual;

    // El constructor asigna los valores a cada constante
    ClaseRiesgo(String descripcion, double tarifaPorcentual) {
        this.descripcion = descripcion;
        this.tarifaPorcentual = tarifaPorcentual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Este método devuelve el valor listo para ser multiplicado por el IBC/Salario
    public double getTarifaPorcentual() {
        return tarifaPorcentual;
    }
}