package com.udc;

import com.udc.adapters.console.EmpleadoAsalariadoConsole;

public class Main {
    public static void main(String[] args) {
        // Iniciar adaptador de consola para EmpleadoAsalariado
        EmpleadoAsalariadoConsole console = new EmpleadoAsalariadoConsole();
        console.run();
    }
}