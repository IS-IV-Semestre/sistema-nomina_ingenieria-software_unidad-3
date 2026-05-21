package com.udc.domain.valueobjects.ARL;

public record DatosEmpresa(String razonSocial, String nit, String actividadEconomicaPrincipal) {
    public DatosEmpresa(String razonSocial, String nit, String actividadEconomicaPrincipal) {
        String razonSocialNormilized = razonSocial.trim();
        String nitNormilized = nit.trim();
        String actividadEconomicaPrincipalNormilized = actividadEconomicaPrincipal.trim();

        String nitPattern = "/^[0-9]{8,10}(-[0-9]{1})?$/";

        if(razonSocialNormilized.isBlank() || nitNormilized.isBlank() || actividadEconomicaPrincipalNormilized.isBlank()){
            throw new IllegalArgumentException("Todos los campos deben tener un valor");
        }

        if(!nitNormilized.matches(nitPattern)){
            throw new IllegalArgumentException("El formato del nit es incorrecto");
        }

        this.razonSocial = razonSocialNormilized;
        this.nit = nitNormilized;
        this.actividadEconomicaPrincipal = actividadEconomicaPrincipalNormilized;
    }
}
