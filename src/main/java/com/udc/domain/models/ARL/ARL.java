package com.udc.domain.models.ARL;

import com.udc.domain.enums.ARL.ClaseRiesgo;
import com.udc.domain.valueobjects.ARL.DatosEmpresa;
import com.udc.domain.valueobjects.ARL.DatosTrabajador;

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
