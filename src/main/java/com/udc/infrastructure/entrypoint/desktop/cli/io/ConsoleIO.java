package com.udc.infrastructure.entrypoint.desktop.cli.io;

import java.io.PrintStream;
import java.util.Scanner;

import static java.lang.System.out;

public final class ConsoleIO {
    private final Scanner entrada;
    private final PrintStream salida;

    public ConsoleIO(final Scanner entrada, final PrintStream salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    public String obtenerRespuesta(final String placeholder) {
        String value;
        do {
            out.print(placeholder);
            value = entrada.nextLine().trim();
            if (value.isBlank()) {
                out.println("  Valor no puede estar vacío. Por favor intente nuevamente.");
            }
        } while (value.isBlank());
        return value;
    }

    public String respuestaOpcional(final String placeholder) {
        out.print(placeholder);
        return entrada.nextLine().trim();
    }

    public int respuestaInt(final String placeholder) {
        while (true) {
            out.print(placeholder);
            final String raw = entrada.nextLine().trim();
            try {
                return Integer.parseInt(raw);
            } catch (final NumberFormatException ignored) {
                out.println("  Respuesta invalida. Por favor ingrese un numero entero.");
            }
        }
    }
    
    public void println(final String message) {
        out.println(message);
    }

    public void println() {
        out.println();
    }

    public void printf(final String format, final Object... args) {
        out.printf(format, args);
    }
}
