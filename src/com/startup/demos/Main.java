package com.startup.demos;

import com.startup.models.DesempenoReport;
import com.startup.models.Empleado;
import com.startup.models.EmpresaRecord;
import com.startup.services.EmpleadoService;
import com.startup.util.InputUtils;

/**
 * Clase de demostración.
 * Muestra el uso de tipos primitivos, Records, NullPointerException mejorado
 * (Java 17+ Helpful NullPointerException) y la lógica del EmpleadoService.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("""
                Encabezado del sistema""");

        // --- Demo: Tipos de datos primitivos ---
        byte n1 = 120;
        short n2 = 30000;
        int n3 = 900000000;
        long n4 = 1000000000000000000L;
        float n5 = 20.15f;
        double n6 = 20.15;
        char l1 = 'n';
        boolean isTrue = true;

        System.out.println("Primitivos -> byte: " + n1 + ", short: " + n2 +
                ", int: " + n3 + ", long: " + n4);
        System.out.println("Primitivos -> float: " + n5 + ", double: " + n6 +
                ", char: " + l1 + ", boolean: " + isTrue);

        // --- Demo: NullPointerException mejorado (Java 17+) ---
        /*
         * En Java 8 el NullPointerException simplemente indicaba que había
         * un error, pero no decía dónde se encontraba. Ahora el "Helpful
         * NullPointerException" indica exactamente dónde está el error,
         * lo que facilita la búsqueda y optimiza el tiempo de debug.
         *
         * Comparar dos objetos (A, B) con (==) compara la dirección de
         * memoria (referencia en HEAP) y no el contenido. Por eso dos
         * objetos distintos con los mismos valores nunca son == entre sí.
         */
        Empleado emp1 = new Desarrollador(100, "Legacy 1", 10000);
        Empleado emp2 = new Desarrollador(101, "Legacy 2", 8000);

        emp1 = null;

        try {
            System.out.println("Empleado: " + emp1);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        // --- Demo: EmpresaRecord ---
        EmpresaRecord miEmpresa = new EmpresaRecord("Mi Nueva Empresa", 9000123, "2005-09-12");
        System.out.println("Empresa: " + miEmpresa);

        // Agregamos algunos datos iniciales para la demo
        EmpleadoService.agregarEmpleado(new Desarrollador(1, "Alice", 2500, "Java"));
        EmpleadoService.agregarEmpleado(new Desarrollador(2, "Bob", 1500, "Python"));
        EmpleadoService.agregarEmpleado(new Desarrollador(3, "Charlie", 3500, "C#"));
        EmpleadoService.agregarEmpleado(new Gerente(4, "David", 5000, 20000.0));

        // --- Demo: Menu Switch Moderno ---
        var opciones = -1;

        do {
            System.out.println("""
                    ====== SISTEMA DE GESTION (MODERNO) ======
                    1) Registrar Empleado 
                    2) Listar Empleados 
                    3) Buscar Empleado (HashMap O(1))
                    4) Ver Primero/Ultimo (Sequenced)
                    5) Ver Lista Inversa
                    6) Eliminar por Bajo Puntaje
                    7) Generar Reporte Final
                    8) Generar Reporte de Desempeño
                    9) Demo InstanceOf (Legacy vs Moderno)
                    0) Salir
                    =========================================""");

            opciones = InputUtils.leerEntero("Seleccione");

            switch (opciones) {
                case 1 -> {
                    int id = InputUtils.leerEntero("ID");
                    String nom = InputUtils.leerString("Nombre");
                    double sal = InputUtils.leerDouble("Salario");
                    int pun = InputUtils.leerEntero("Puntaje");
                    String lang = InputUtils.leerString("Lenguaje Principal");
                    EmpleadoService.agregarEmpleado(new Desarrollador(id, nom, sal, lang));
                }
                case 2 -> EmpleadoService.listarEmpleados();
                case 3 -> {
                    int id = InputUtils.leerEntero("Ingrese ID a buscar");
                    var emp = EmpleadoService.buscarEmpleado(id);
                    if (emp != null) {
                        System.out.println("Encontrado: " + emp.getNombre() + " | Puntaje: " + emp.getPuntajeTest());
                    } else {
                        System.out.println("No se encontró el empleado.");
                    }
                }
                case 4 -> {
                    var primero = EmpleadoService.obtenerPrimerEmpleado();
                    var ultimo = EmpleadoService.obtenerUltimoEmpleado();
                    System.out.println("Primer Empleado: " + (primero != null ? primero.getNombre() : "Vacio"));
                    System.out.println("Ultimo Empleado: " + (ultimo != null ? ultimo.getNombre() : "Vacio"));
                }
                case 5 -> {
                    System.out.println("--- Lista en Orden Inverso ---");
                    for (var emp : EmpleadoService.obtenerListaInversa()) {
                        System.out.println(emp.getNombre());
                    }
                }
                case 6 -> {
                    int min = InputUtils.leerEntero("Puntaje minimo");
                    EmpleadoService.eliminarPorBajoPuntaje(min);
                }
                case 7 -> EmpleadoService.generarReporteFinal();
                case 8 -> {
                    int id = InputUtils.leerEntero("Ingrese ID para el reporte");
                    var emp = EmpleadoService.buscarEmpleado(id);
                    if (emp != null) {
                        DesempenoReport reporte = EmpleadoService.generarReporteIndividual(emp);
                        System.out.println("REPORTE DE DESEMPEÑO");
                        System.out.println(reporte);
                    } else {
                        System.out.println("No se encontró el empleado.");
                    }
                }
                case 9 -> {
                    int id = InputUtils.leerEntero("Ingrese ID para demo de casting");
                    var emp = EmpleadoService.buscarEmpleado(id);
                    if (emp != null) {
                        EmpleadoService.mostrarHabilidadLegacy(emp);
                        EmpleadoService.mostrarHabilidadModerna(emp);
                    } else {
                        System.out.println("No se encontró el empleado.");
                    }
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida");
            }
        } while (opciones != 0);
    }
}
