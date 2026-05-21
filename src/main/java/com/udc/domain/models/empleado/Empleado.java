package com.udc.domain.models.empleado;

import com.udc.domain.enums.empleado.tipoDocumento;
import com.udc.domain.models.ARL.ARL;
import com.udc.domain.valueobjects.ARL.DatosEmpresa;
import com.udc.domain.valueobjects.ARL.DatosTrabajador;
import com.udc.domain.valueobjects.empleado.EmpleadoApellido;
import com.udc.domain.valueobjects.empleado.EmpleadoId;
import com.udc.domain.valueobjects.empleado.EmpleadoNombre;

import java.util.UUID;

public abstract class Empleado {

    private EmpleadoId id;
    private EmpleadoNombre nombre;
    private EmpleadoApellido apellido;
    private int antiguedadAnios;
    private ARL arl;
    private tipoDocumento tipoDocumento;
    private EmpleadoDocumento documento;


    public Empleado( String nombre, String apellido, int antiguedadAnios, tipoDocumento tipoDocumento, String documento) {
        if (antiguedadAnios < 0) {
            throw new IllegalArgumentException("La antigüedad no puede ser negativa.");
        }
        this.id = new EmpleadoId(UUID.randomUUID().toString());
        this.nombre = new EmpleadoNombre(nombre);
        this.antiguedadAnios = antiguedadAnios;
        this.apellido = new EmpleadoApellido(apellido);
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
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
    public String getId() { return id.toString(); }
    public String getNombre() { return nombre.toString(); }
    public String getApellido() { return apellido.toString(); }
    public int getAntiguedadAnios() { return antiguedadAnios; }
    public ARL getArl() { return arl; }
    public tipoDocumento getTipoDocumento() { return tipoDocumento; }
    public String getDocumento() { return documento; }
}
