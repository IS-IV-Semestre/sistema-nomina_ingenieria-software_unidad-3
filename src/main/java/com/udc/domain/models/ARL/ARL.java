package com.udc.domain.models.ARL;

public class ARL {
    DatosEmpresa datosEmpresa;
    DatosTrabajador trabajador;

    public ARL() {
    }

    public ARL(DatosEmpresa datosEmpresa, DatosTrabajador trabajador) {
        this.datosEmpresa = datosEmpresa;
        this.trabajador = trabajador;
    }

    public ClaseRiesgo getClaseRiesgo() {
        return trabajador.riesgo();
    }
}
