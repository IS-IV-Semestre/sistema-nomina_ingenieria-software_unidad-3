package com.udc.domain.models.empleado;

import com.udc.domain.models.ARL.ARL;
import com.udc.domain.models.ARL.DatosEmpresa;
import com.udc.domain.models.ARL.DatosTrabajador;

import java.util.List;

public abstract class Empleado {

    private String id;
    private String nombre;
    private String apellido;
    private int antiguedadAnios;
    private ARL arl;


    public Empleado(String id, String nombre, String apellido, int antiguedadAnios) {
        if (antiguedadAnios < 0) {
            throw new IllegalArgumentException("La antigüedad no puede ser negativa.");
        }
        this.id = id;
        this.nombre = nombre;
        this.antiguedadAnios = antiguedadAnios;
        this.apellido = apellido;
        this.arl = null;
    }

    // Métodos abstractos que cada tipo de empleado resolverá de forma independiente (Abierto/Cerrado)
    public abstract double calcularSalarioBruto();
    public abstract double calcularBeneficios();

    // Lógica común: Deducción obligatoria del 4% de Seguro Social y Pensión sobre el bruto (Responsabilidad Única)
    public double calcularDeducciones() {
        return calcularSalarioBruto() * 0.04;
    }

    // Cálculo final del Salario Neto con la validación obligatoria
    public double calcularSalarioNeto() {
        double neto = calcularSalarioBruto() + calcularBeneficios() - calcularDeducciones();
        if (neto < 0) {
            throw new IllegalStateException("Error: El salario neto del empleado no puede ser negativo.");
        }
        return neto;
    }

    // Aporte obligatorio que asume la empresa (Para el reporte de costos de nómina)
    public double calcularCostoARL() {
        // Suponiendo que tu clase ARL tiene el método para obtener el porcentaje listo para multiplicar
        return calcularSalarioBruto() * arl.getClaseRiesgo().getTarifaPorcentual();
    }

    public void registrarArl(DatosEmpresa datosEmpresa, DatosTrabajador trabajador){
        arl = new ARL(datosEmpresa, trabajador);
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getAntiguedadAnios() { return antiguedadAnios; }
    public ARL getArl() { return arl; }
}
