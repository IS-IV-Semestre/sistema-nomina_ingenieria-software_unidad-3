package com.udc.domain.valueobjects.ARL;

import com.udc.domain.enums.ARL.ClaseRiesgo;

public record DatosTrabajador(String nombres, String documentoIdentidad, double salarioBase, ClaseRiesgo riesgo) {
}
