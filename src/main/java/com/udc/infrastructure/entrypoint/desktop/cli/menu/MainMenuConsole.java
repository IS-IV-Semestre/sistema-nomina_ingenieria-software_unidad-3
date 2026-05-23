package com.udc.infrastructure.entrypoint.desktop.cli.menu;

import com.udc.adapters.console.EmpleadoAsalariadoConsole;
import com.udc.adapters.console.EmpleadoPorComisionConsole;
import com.udc.adapters.console.EmpleadoPorHorasConsole;
import com.udc.adapters.console.EmpleadoTemporalConsole;
import com.udc.application.service.*;
import com.udc.application.service.dto.command.*;
import com.udc.application.service.dto.query.GetEmpleadoByIdQuery;
import com.udc.domain.enums.empleado.tipoDocumento;
import com.udc.domain.enums.empleado.tipoEmpleado;
import com.udc.domain.models.empleado.*;
import com.udc.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;

import java.io.PrintStream;
import java.util.Scanner;

public class MainMenuConsole {

    private static final Scanner input = new Scanner(System.in);
    private static final PrintStream output = new PrintStream(System.out);
    private static final ConsoleIO consoleIO = new ConsoleIO(input, output);
    private static final GetEmpleadosService getAllService = new GetEmpleadosService();
    private static final DeleteEmpleadoService deleteService = new DeleteEmpleadoService();
    private static final GetEmpleadoById getByIdService = new GetEmpleadoById();

    public static void run() {
        try {
            while (true) {
                showMenu();
                int option = consoleIO.respuestaInt("Seleccione una opcion: ");

                if (option == 5) {
                    System.out.println("Cerrando Sistema...");
                    System.exit(0);
                }

                if (option < 1 || option > 5) {
                    System.out.println("\n\tOpción invalida, por favor seleccione una opción valida\n");
                } else {
                    loadingOption(option);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showMenu() {
        System.out.println("\nBIENVENID@ AL MODULO DE EMPLEADO");
        for (MenuPrincipalOptions option : MenuPrincipalOptions.values()) {
            System.out.printf("%d. %s\n", option.getNumber(), option.getDescription());
        }
    }

    public static void loadingOption(int option) {
        System.out.println(" ");
        String id;
        switch (option) {

            case 1:
                var empleados = getAllService.execute();
                if (empleados == null || empleados.isEmpty()) {
                    System.out.println("No se encontraron empleados registrados en el sistema.");
                    break;
                }
                System.out.println("=".repeat(50));
                System.out.println("| Empleados encontrados: " + empleados.size());
                System.out.println("=".repeat(50));
                for (Empleado e : empleados) {
                    System.out.printf("| %-20s | %-36s | %-10s |\n",
                            e.getNombre() + " " + e.getApellido(), e.getId(), e.getTipoDocumento().name());
                }
                System.out.println("=".repeat(50));
                consoleIO.respuestaOpcional("\nPresione cualquier tecla para volver al menu principal\n");
                break;

            case 2:
                System.out.println("Seleccione el tipo de empleado que desea registrar:");
                tipoEmpleado[] tipos = tipoEmpleado.values();
                for (tipoEmpleado tipo : tipos) {
                    System.out.printf("%d. %s\n", tipo.ordinal() + 1, tipo.name().replaceAll("_", " "));
                }
                int tipoIdx = consoleIO.respuestaInt("Seleccione una opción (1 - " + tipos.length + "): ") - 1;
                if (tipoIdx < 0 || tipoIdx >= tipos.length) {
                    System.out.println("Tipo de empleado inválido.");
                    break;
                }
                switch (tipos[tipoIdx]) {
                    case ASALARIADO:
                        new EmpleadoAsalariadoConsole().run();
                        break;
                    case POR_HORAS:
                        new EmpleadoPorHorasConsole().run();
                        break;
                    case POR_COMISION:
                        new EmpleadoPorComisionConsole().run();
                        break;
                    case EMPLEADO_TEMPORAL:
                        new EmpleadoTemporalConsole().run();
                        break;
                }
                break;

            case 3:
                id = consoleIO.obtenerRespuesta("Digite el ID del empleado a actualizar: ");
                try {
                    Empleado found = getByIdService.execute(new GetEmpleadoByIdQuery(id));
                    System.out.println("Empleado: " + found.getNombre() + " " + found.getApellido());

                    String nombre    = consoleIO.obtenerRespuesta("Nuevo nombre: ");
                    String apellido  = consoleIO.obtenerRespuesta("Nuevo apellido: ");
                    int antiguedad   = consoleIO.respuestaInt("Nueva antigüedad (años): ");
                    tipoDocumento td = seleccionarTipoDocumento();
                    String documento = consoleIO.obtenerRespuesta("Nuevo documento: ");

                    if (found instanceof EmpleadoAsalariado) {
                        double salario = Double.parseDouble(consoleIO.obtenerRespuesta("Nuevo salario mensual: "));
                        new UpdateEmpleadoService().execute(
                                new UpdateEmpleadoAsalariadoCommand(id, nombre, apellido, td, documento, antiguedad, null, salario));

                    } else if (found instanceof EmpleadoPorHoras) {
                        double tarifa  = Double.parseDouble(consoleIO.obtenerRespuesta("Nueva tarifa por hora: "));
                        double horas   = Double.parseDouble(consoleIO.obtenerRespuesta("Nuevas horas trabajadas: "));
                        boolean fondo  = consoleIO.obtenerRespuesta("¿Acepta fondo de ahorro? (s/n): ").equalsIgnoreCase("s");
                        new UpdateEmpleadoPorHorasService().execute(
                                new UpdateEmpleadoPorHorasCommand(id, nombre, apellido, td, documento, antiguedad, null, tarifa, horas, fondo));

                    } else if (found instanceof EmpleadoPorComision) {
                        double base    = Double.parseDouble(consoleIO.obtenerRespuesta("Nuevo salario base: "));
                        double comision = Double.parseDouble(consoleIO.obtenerRespuesta("Nuevo porcentaje comisión (ej. 0.05): "));
                        double ventas  = Double.parseDouble(consoleIO.obtenerRespuesta("Nuevas ventas totales: "));
                        new UpdateEmpleadoPorComisionService().execute(
                                new UpdateEmpleadoPorComisionCommand(id, nombre, apellido, td, documento, antiguedad, null, base, comision, ventas));

                    } else if (found instanceof EmpleadoTemporal) {
                        int duracion   = consoleIO.respuestaInt("Nueva duración del contrato (meses): ");
                        double salFijo = Double.parseDouble(consoleIO.obtenerRespuesta("Nuevo salario mensual fijo: "));
                        new UpdateEmpleadoTemporalService().execute(
                                new UpdateEmpleadoTemporalCommand(id, nombre, apellido, td, documento, null, duracion, salFijo));
                    }

                    System.out.println("Empleado actualizado con éxito.");
                } catch (Exception e) {
                    System.out.println("Error al actualizar: " + e.getMessage());
                }
                consoleIO.respuestaOpcional("\nPresione cualquier tecla para volver al menu principal\n");
                break;

            case 4:
                id = consoleIO.obtenerRespuesta("Digite el ID del empleado a eliminar: ");
                try {
                    Empleado empleado = getByIdService.execute(new GetEmpleadoByIdQuery(id));
                    String confirmacion = consoleIO.obtenerRespuesta(
                            "¿Está seguro de eliminar a " + empleado.getNombre() + " " + empleado.getApellido() + "? (S/N): ");
                    if (confirmacion.equalsIgnoreCase("S")) {
                        deleteService.execute(new DeleteEmpleadoCommand(id));
                        System.out.println("Empleado eliminado con éxito.");
                    } else {
                        System.out.println("Eliminación cancelada.");
                    }
                } catch (Exception e) {
                    System.out.println("Error al eliminar: " + e.getMessage());
                }
                consoleIO.respuestaOpcional("\nPresione cualquier tecla para volver al menu principal\n");
                break;
        }
    }

    private static tipoDocumento seleccionarTipoDocumento() {
        System.out.println("Seleccione tipo de documento:");
        tipoDocumento[] valores = tipoDocumento.values();
        for (int i = 0; i < valores.length; i++) {
            System.out.println((i + 1) + ". " + valores[i].name());
        }
        while (true) {
            int op = consoleIO.respuestaInt("Opción: ");
            if (op >= 1 && op <= valores.length) return valores[op - 1];
            System.out.println("Opción inválida, intente de nuevo.");
        }
    }
}
