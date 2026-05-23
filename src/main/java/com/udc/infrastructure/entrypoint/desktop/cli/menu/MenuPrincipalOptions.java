package com.udc.infrastructure.entrypoint.desktop.cli.menu;

public enum MenuPrincipalOptions {
    LISTAR_EMPLEADOS(1,"Listar todos los empleados"),
    REGISTRAR_EMPLEADO(2, "Registrar un nuevo empleado"),
    ACTUALIZAR_EMPLEADO(3, "Actualizar un empleado"),
    ELIMINAR_EMPLEADO(4, "Eliminar un empleado"),
    EXIT(5,"Salir");

    private final int number;
    private final String description;

    MenuPrincipalOptions(int number, String description) {
        if (number < 1 || number > 5) {
            throw new IllegalArgumentException("Opción invalida, por favor seleccione un número entre 1 y 5.");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("La descripción de la opción no puede ser nula o vacía.");
        }
        this.number = number;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }
}
