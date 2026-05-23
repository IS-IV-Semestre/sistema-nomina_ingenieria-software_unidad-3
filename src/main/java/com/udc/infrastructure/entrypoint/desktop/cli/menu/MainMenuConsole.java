package com.udc.infrastructure.entrypoint.desktop.cli.menu;

import com.udc.application.service.DeleteEmpleadoService;
import com.udc.application.service.GetEmpleadoById;
import com.udc.application.service.GetEmpleadosService;
import com.udc.application.service.dto.command.DeleteEmpleadoCommand;
import com.udc.application.service.dto.query.GetEmpleadoByIdQuery;
import com.udc.domain.enums.empleado.tipoEmpleado;
import com.udc.domain.models.empleado.Empleado;
import com.udc.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import org.w3c.dom.ls.LSOutput;

import java.io.PrintStream;
import java.util.Scanner;

public class MainMenuConsole {

    private static Scanner input = new Scanner(System.in);
    private static PrintStream output = new PrintStream(System.out);
    private static ConsoleIO consoleIO = new ConsoleIO(input,output);
    private static GetEmpleadosService getAllService = new GetEmpleadosService();
    private static DeleteEmpleadoService deleteService = new DeleteEmpleadoService();
    private static GetEmpleadoById getByIdService = new GetEmpleadoById();

    public static void run(){
        try{

            while (true){
                showMenu();
                int option = consoleIO.respuestaInt("Seleccione una opcion: ");

                if(option == 5){
                    System.exit(0);
                    break;
                }

                if(option > 5 || option < 1){
                    System.out.println("\n\tOpción invalida, por favor selecciones una opción valida\n");
                }else{
                    loadingOption(option);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static void showMenu(){
        System.out.println("BIENVENID@ AL MODULO DE EMPLEADO");
        for(MenuPrincipalOptions option: MenuPrincipalOptions.values()){
            System.out.printf("%d. %s\n",option.getNumber(), option.getDescription());
        }
    }

    public static void loadingOption(int option){
        System.out.println(" ");
        String id;
        switch (option){
            case 1: // Lista Empleados

                if(getAllService.execute() == null){
                    System.out.println("No se encontraron empleados registrados en el sistema");
                }

                int total = getAllService.execute().size();


                System.out.println("=".repeat(30));
                System.out.println("| Empleados encontrados " + total);
                System.out.println("=".repeat(30));


                for(Empleado empleado: getAllService.execute()){
                    System.out.println("| Nombre | ID | Tipo |");
                    System.out.printf("| %s | %s | %s |\n", empleado.getNombre(), empleado.getId(),empleado.getTipoDocumento().name());
                }

                consoleIO.respuestaOpcional("Presione cualquier tecla para volver al menu principal\n");
                System.out.println(" ");
                break;
            case 2:
                System.out.println("Selecione el tipo de empleado que desea registrar");
                for(tipoEmpleado tipo: tipoEmpleado.values()){
                    System.out.printf("%d. %s\n",tipo.ordinal()+1,tipo.name().replaceAll("_"," "));
                }
                int tipoSelected = consoleIO.respuestaInt("Selecione una opción (1 - 4): ") - 1;

                // insertar run para el tipo

                System.out.println("Presione cualquier tecla para volver al menu principal");
                break;
            case 3:
                id = consoleIO.obtenerRespuesta("Digite el id del empleado: ");

                // insertar run para actualizar empleado

                consoleIO.respuestaOpcional("Presione cualquier tecla para volver al menu principal \n");
                id = null;
            case 4:
                id = consoleIO.obtenerRespuesta("Digite el id del empleado: ");

                DeleteEmpleadoCommand deleteInfo = new DeleteEmpleadoCommand(id);
                GetEmpleadoByIdQuery query = new GetEmpleadoByIdQuery(id);
                Empleado empleado = getByIdService.execute(query);

                boolean confirmed = false;
                while(!confirmed){
                    String deleteConfirm = consoleIO.obtenerRespuesta("Estas seguro de eliminar a " + empleado.getNombre() + " ? (S/N)");
                    if(deleteConfirm.equalsIgnoreCase("S")){
                        confirmed = true;
                    } else if (deleteConfirm.equalsIgnoreCase("n")) {
                        System.out.println("Eliminación cancelada");
                    }
                    System.out.println("Respuesta invalida, por favor intente de nuevo\n");
                }

                consoleIO.respuestaOpcional("Presione cualquier tecla para volver al menu principal \n");
                id = null;
            break;
            case 5:
                System.out.println("Cerrando Systema...");
                break;
        }
    }
}
