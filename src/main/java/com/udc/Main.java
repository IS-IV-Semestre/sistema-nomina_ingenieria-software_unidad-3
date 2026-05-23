package com.udc;

import com.udc.adapters.console.EmpleadoAsalariadoConsole;
import com.udc.adapters.console.EmpleadoTemporalConsole;

public class Main {
    public static void main(String[] args) {
        // Iniciar adaptador de consola para EmpleadoAsalariado
        EmpleadoAsalariadoConsole console = new EmpleadoAsalariadoConsole();
        console.run();
        // Iniciar adaptador de consola para EmpleadoTemporal
        EmpleadoTemporalConsole temporalConsole = new EmpleadoTemporalConsole();
        temporalConsole.run();
    }
}