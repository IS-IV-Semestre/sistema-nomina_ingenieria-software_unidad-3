package com.udc.adapters.console;

import java.util.Scanner;

import com.udc.application.empleado.EmpleadoTemporalService;
import com.udc.application.service.CreateEmpleadoTemporalService;
import com.udc.application.service.dto.command.CreateEmpleadoTemporalCommand;
import com.udc.domain.enums.empleado.tipoDocumento;
import com.udc.domain.models.empleado.EmpleadoTemporal;


/**
 Adaptador de consola (puerto/adaptador) para interacción con EmpleadoTemporal.
*/

public class EmpleadoTemporalConsole {
    private final EmpleadoTemporalService service = new EmpleadoTemporalService();
    private final Scanner scanner = new Scanner(System.in);
    private EmpleadoTemporal current = null;

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
                    mostrarDuracionContrato();
                    break;
                case 7:
                    mostrarDatosEmpleado();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
            System.out.println();
        }
    }
    private void mostrarMenu() {
        System.out.println("=== Empleado Temporal ===");
        System.out.println("1. Crear Empleado Temporal");
        System.out.println("2. Mostrar salario bruto");
        System.out.println("3. Mostrar beneficios");
        System.out.println("4. Mostrar deducciones");
        System.out.println("5. Mostrar salario neto");
        System.out.println("6. Mostrar duración del contrato");
        System.out.println("7. Mostrar datos del empleado");
        System.out.println("0. Salir");
    }
    private void crearEmpleado() {
        System.out.println("--- Crear Empleado Temporal ---");
        String nombre = leerLinea("Nombre: ");
        String apellido = leerLinea("Apellido: ");
        int duracionContrato = leerEntero("Duración del contrato (meses): ");
        double salarioMensualFijo = leerDouble("Salario mensual: ");
        tipoDocumento tipoDoc = seleccionarTipoDocumento();
        String documento = leerLinea("Documento: ");

        try {
            CreateEmpleadoTemporalCommand command = new CreateEmpleadoTemporalCommand(
                    nombre, apellido, tipoDoc, documento, duracionContrato, salarioMensualFijo);
            current = new CreateEmpleadoTemporalService().execute(command);
            System.out.println("Empleado temporal creado con éxito. ID: " + current.getId());
        } catch (IllegalArgumentException ex) {
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
            System.out.println("Salario bruto: " + service.obtenerSalarioBruto(current));
        }
    }

    private void mostrarBeneficios() {
        if (validarEmpleadoCreado()) {
            System.out.println("Beneficios: " + service.obtenerBeneficios(current));
        }
    }

    private void mostrarDeducciones() {
        if (validarEmpleadoCreado()) {
            System.out.println("Deducciones: " + service.obtenerDeducciones(current));
        }
    }

    private void mostrarSalarioNeto() {
        if (validarEmpleadoCreado()) {
            try {
                System.out.println("Salario neto: " + service.obtenerSalarioNeto(current));
            } catch (IllegalStateException ex) {
                System.out.println("Error al calcular salario neto: " + ex.getMessage());
            }
        }
    }

    private void mostrarDuracionContrato() {
        if (validarEmpleadoCreado()) {
            System.out.println("Duración del Contrato: " + current.getDuracionContrato() + " meses");
        }
    }

    private void mostrarDatosEmpleado() {
        if (validarEmpleadoCreado()) {
            System.out.println("ID: " + current.getId());
            System.out.println("Nombre: " + current.getNombre());
            System.out.println("Apellido: " + current.getApellido());
            System.out.println("Tipo Documento: " + current.getTipoDocumento());
            System.out.println("Documento: " + current.getDocumento());
            System.out.println("Duración del contrato: " + current.getDuracionContrato() + " meses");
            System.out.println("Salario mensual fijo: " + current.getSalarioMensualFijo());
        }
    }

    private boolean validarEmpleadoCreado() {
        if (current == null) {
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
                System.out.println("Entrada inválida, ingrese un número (decimal permitido).");
            }
        }
    }



}
