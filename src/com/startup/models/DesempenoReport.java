package com.startup.models;

// Reporte inmutable para el cierre del mes

public record DesempenoReport(
        int idEmpleado,
        double promedio,
        String feedback
) {
}
