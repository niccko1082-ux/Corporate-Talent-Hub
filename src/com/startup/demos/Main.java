package com.startup.demos;

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
        Empleado emp1 = new Empleado(100, "Legacy 1", 200, 10000, 90, 20, 1, false);
        Empleado emp2 = new Empleado(101, "Legacy 2", 1800000, 8000, 80, 25, 2, true);

        emp1 = null;

        try {
            System.out.println("Empleado: " + emp1);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        // --- Demo: EmpresaRecord ---
        EmpresaRecord miEmpresa = new EmpresaRecord("Mi Nueva Empresa", 9000123, "2005-09-12");
        System.out.println("Empresa: " + miEmpresa);

        // --- Demo: EmpleadoService ---
        // Agregamos algunos datos iniciales para la demo
        EmpleadoService.agregarEmpleado(new Empleado(1, "Alice", 2500, 500, 95, 25, 1, true));
        EmpleadoService.agregarEmpleado(new Empleado(2, "Bob", 1500, 300, 80, 30, 2, true));
        EmpleadoService.agregarEmpleado(new Empleado(3, "Charlie", 3500, 800, 88, 28, 1, true));

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
                    0) Salir
                    =========================================""");

            opciones = InputUtils.leerEntero("Seleccione");

            switch (opciones) {
                case 1 -> {
                    int id = InputUtils.leerEntero("ID");
                    String nom = InputUtils.leerString("Nombre");
                    double sal = InputUtils.leerDouble("Salario");
                    int pun = InputUtils.leerEntero("Puntaje");
                    EmpleadoService.agregarEmpleado(new Empleado(id, nom, sal, 500, pun, 25, 1, true));
                }
                case 2 -> EmpleadoService.listarEmpleados();
                case 3 -> {
                    int id = InputUtils.leerEntero("Ingrese ID a buscar");
                    var emp = EmpleadoService.buscarEmpleado(id);
                    if (emp != null) {
                        System.out.println("Encontrado: " + emp.nombre + " | Puntaje: " + emp.puntajeTest);
                    } else {
                        System.out.println("No se encontró el empleado.");
                    }
                }
                case 4 -> {
                    var primero = EmpleadoService.obtenerPrimerEmpleado();
                    var ultimo = EmpleadoService.obtenerUltimoEmpleado();
                    System.out.println("Primer Empleado: " + (primero != null ? primero.nombre : "Vacio"));
                    System.out.println("Ultimo Empleado: " + (ultimo != null ? ultimo.nombre : "Vacio"));
                }
                case 5 -> {
                    System.out.println("--- Lista en Orden Inverso ---");
                    for (var emp : EmpleadoService.obtenerListaInversa()) {
                        System.out.println(emp.nombre);
                    }
                }
                case 6 -> {
                    int min = InputUtils.leerEntero("Puntaje minimo");
                    EmpleadoService.eliminarPorBajoPuntaje(min);
                }
                case 7 -> EmpleadoService.generarReporteFinal();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida");
            }
        } while (opciones != 0);
    }
}
