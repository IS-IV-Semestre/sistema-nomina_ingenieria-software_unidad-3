package com.udc.adapters.console;

import java.util.Scanner;

import com.udc.application.empleado.EmpleadoPorComisionService;
import com.udc.application.service.CreateEmpleadoPorComisionService;
import com.udc.application.service.dto.command.CreateEmpleadoPorComisionCommand;
import com.udc.domain.enums.empleado.tipoDocumento;
import com.udc.domain.exceptions.DomainException;
import com.udc.domain.models.empleado.EmpleadoPorComision;

public class EmpleadoPorComisionConsole {
    // puente
    private final EmpleadoPorComisionService service = new EmpleadoPorComisionService();
    
    // El lector para capturar lo que escriba el usuario por teclado
    private final Scanner scanner = new Scanner(System.in);
    
    private EmpleadoPorComision empleadoActual = null;

    // Arranca el menú 
    public void run() {
        while (true) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1:
                    crearEmpleado();
                    break;
                case 2:
                    mostrarSalarioBruto();
                    break;
                case 3:
                    mostrarBeneficios();
                    break;
                case 4:
                    mostrarDeducciones();
                    break;
                case 5:
                    mostrarSalarioNeto();
                    break;
                case 6:
                    mostrarDatosEmpleado();
                    break;
                case 0:
                    System.out.println("Saliendo del menú de Comisión...");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
            System.out.println();
        }
    }

    private void mostrarMenu() {
        System.out.println("--- Menú Empleado por Comisión ---");
        System.out.println("1. Crear empleado por comisión");
        System.out.println("2. Mostrar salario bruto");
        System.out.println("3. Mostrar beneficios");
        System.out.println("4. Mostrar deducciones");
        System.out.println("5. Mostrar salario neto");
        System.out.println("6. Mostrar datos del empleado");
        System.out.println("0. Salir");
    }

    // Pide los datos por pantalla y deja que el dominio valide 
    private void crearEmpleado() {
        System.out.println("--- Crear Empleado por Comisión ---");
        String nombre = leerLinea("Nombre: ");
        String apellido = leerLinea("Apellido: ");
        int antiguedad = leerEntero("Antigüedad (años): ");
        tipoDocumento tipoDoc = seleccionarTipoDocumento();
        String documento = leerLinea("Documento: ");
        
        double salarioBase = leerDouble("Salario Base: ");
        double porcentajeComision = leerDouble("Porcentaje comisión (ej. 0.05 para 5%): ");
        double ventas = leerDouble("Ventas totales del mes: ");

        try {
            CreateEmpleadoPorComisionCommand command = new CreateEmpleadoPorComisionCommand(
                    nombre, apellido, tipoDoc, documento, antiguedad,
                    salarioBase, porcentajeComision, ventas);
            empleadoActual = new CreateEmpleadoPorComisionService().execute(command);
            System.out.println("Empleado por comisión creado con éxito. ID: " + empleadoActual.getId());
        } catch (Exception ex) {
            System.out.println("Error al crear empleado: " + ex.getMessage());
        }
    }

    private tipoDocumento seleccionarTipoDocumento() {
        System.out.println("Seleccione tipo de documento:");
        tipoDocumento[] valores = tipoDocumento.values();
        for (int i = 0; i < valores.length; i++) {
            System.out.println((i + 1) + ". " + valores[i].name());
        }
        while (true) {
            int opcion = leerEntero("Opción: ");
            if (opcion >= 1 && opcion <= valores.length) {
                return valores[opcion - 1];
            }
            System.out.println("Opción inválida, intente de nuevo.");
        }
    }

    private void mostrarSalarioBruto() {
        if (validarEmpleadoCreado()) {
            System.out.println("Salario bruto: " + service.obtenerSalarioBruto(empleadoActual));
        }
    }

    private void mostrarBeneficios() {
        if (validarEmpleadoCreado()) {
            System.out.println("Beneficios: " + service.obtenerBeneficios(empleadoActual));
        }
    }

    private void mostrarDeducciones() {
        if (validarEmpleadoCreado()) {
            System.out.println("Deducciones: " + service.obtenerDeducciones(empleadoActual));
        }
    }

    private void mostrarSalarioNeto() {
        if (validarEmpleadoCreado()) {
            try {
                System.out.println("Salario neto: " + service.obtenerSalarioNeto(empleadoActual));
            } catch (DomainException ex) {
                System.out.println("Error al calcular salario neto: " + ex.getMessage());
            }
        }
    }

    private void mostrarDatosEmpleado() {
        if (validarEmpleadoCreado()) {
            System.out.println("ID: " + empleadoActual.getId());
            System.out.println("Nombre: " + empleadoActual.getNombre());
            System.out.println("Apellido: " + empleadoActual.getApellido());
            System.out.println("Antigüedad (años): " + empleadoActual.getAntiguedadAnios());
            System.out.println("Tipo Documento: " + empleadoActual.getTipoDocumento());
            System.out.println("Documento: " + empleadoActual.getDocumento());
            System.out.println("Salario base: " + empleadoActual.getSalarioBase());
            System.out.println("Porcentaje comisión: " + (empleadoActual.getPorcentajeComision() * 100) + "%");
            System.out.println("Ventas totales: " + empleadoActual.getVentasTotales());
        }
    }

    // Verifica que la caja no esté vacía antes de pedir datos al servicio
    private boolean validarEmpleadoCreado() {
        if (empleadoActual == null) {
            System.out.println("No hay un empleado creado. Seleccione la opción 1 para crearlo.");
            return false;
        }
        return true;
    }

    private String leerLinea(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int leerEntero(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida, ingrese un número entero.");
            }
        }
    }

    private double leerDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine();
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Entrada inválida, ingrese un número decimal.");
            }
        }
    }
}