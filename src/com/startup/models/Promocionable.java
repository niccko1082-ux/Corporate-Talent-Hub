package com.startup.models;

public interface Promocionable {
    /**
     * Calcula el bono económico otorgado al empleado por un ascenso.
     */
    double calcularBonoAscenso();

    /**
     * Método default (Java 8+): Permite añadir funcionalidad a la interfaz
     * sin obligar a las clases hijas a implementarlo manualmente.
     */
    default void registrarLog(String mensaje) {
        System.out.println("[AUDITORIA] Operación de promoción: " + mensaje);
    }
}
