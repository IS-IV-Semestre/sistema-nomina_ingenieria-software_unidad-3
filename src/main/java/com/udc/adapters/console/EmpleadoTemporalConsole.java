package com.udc.adapters.console;

import java.util.Scanner;

import com.udc.application.empleado.EmpleadoTemporalService;
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
                    mostrarSalarioMensualFijo();
                    break;
                case 3:
                    mostrarBeneficios();
                    break;
                case 4:
                    mostrarDuracionContrato();
                    break;
                case 5:
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
        System.out.println("2. Mostrar Salario Mensual Fijo");
        System.out.println("3. Mostrar Beneficios");
        System.out.println("4. Mostrar Duración del Contrato");
        System.out.println("5. Mostrar Datos del Empleado");
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
            current = service.crearEmpleadoTemporal(nombre, apellido, duracionContrato,  tipoDoc, documento, salarioMensualFijo);
            System.out.println("Empleado creado con éxito. ID: " + current.getId());
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

    private void mostrarSalarioMensualFijo() {
        if (current == null) {
            System.out.println("No hay empleado temporal creado.");
            return;
        }
        System.out.println("Salario Mensual Fijo: " + current.getSalarioMensualFijo());
    }

    private void mostrarBeneficios() {
        if (current == null) {
            System.out.println("No hay empleado temporal creado.");
            return;
        }
        System.out.println("Beneficios: " + service.obtenerBeneficios(current));
    }

    private void mostrarDuracionContrato() {
        if (current == null) {
            System.out.println("No hay empleado temporal creado.");
            return;
        }
        System.out.println("Duración del Contrato: " + current.getDuracionContrato() + " meses");
    }

    private void mostrarDatosEmpleado() {
        if (validarEmpleadoCreado()) {
            System.out.println("ID: " + current.getId());
            System.out.println("Nombre: " + current.getNombre());
            System.out.println("Apellido: " + current.getApellido());
            System.out.println("Duración del Contrato: " + current.getDuracionContrato() + " meses");
            System.out.println("Tipo Documento: " + current.getTipoDocumento());
            System.out.println("Documento: " + current.getDocumento());
            System.out.println("Salario mensual: " + current.getSalarioMensualFijo());
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
