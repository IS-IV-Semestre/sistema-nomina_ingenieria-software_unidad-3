package com.udc.application.empleado;

import com.udc.domain.enums.empleado.tipoDocumento;
import com.udc.domain.models.empleado.EmpleadoPorComision;

/* 
   Esta clase es el "Servicio". En la arquitectura que estamos usando, funciona como un puente.
   Su único trabajo es recibir los datos que vienen desde la consola y pasárselos al modelo 
 para que nadie desde afuera toque el modelo directamente.
*/
public class EmpleadoPorComisionService {

    // Este es el constructor vacío del servicio, sirve simplemente para poder crear el puente. 
    public EmpleadoPorComisionService() {}

    /* 
       Aquí recibimos todos los datos que el usuario escribió en la consola como: (nombre, cédula, ventas, etc.).
       Lo único que hacemos es empaquetar todo eso y disparar el constructor de "EmpleadoPorComision" 
    */
    public EmpleadoPorComision crearEmpleado
    (String nombre, String apellido, int antiguedadAnios, tipoDocumento tipoDocumento, String documento, double salarioBase, double porcentajeComision, double ventasTotales) 
    
    {
        return new EmpleadoPorComision(nombre, apellido, antiguedadAnios, tipoDocumento, documento, salarioBase, porcentajeComision, ventasTotales);
    }

    public double obtenerSalarioBruto(EmpleadoPorComision e) {
        return e.calcularSalarioBruto();
    }

    public double obtenerBeneficios(EmpleadoPorComision e) {
        return e.calcularBeneficios();
    }

    public double obtenerDeducciones(EmpleadoPorComision e) {
        return e.calcularDeducciones();
    }

    public double obtenerSalarioNeto(EmpleadoPorComision e) {
        return e.calcularSalarioNeto();
    }



}