package com.startup.services;

import com.startup.models.DesempenoReport;
import com.startup.models.Empleado;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpleadoService {

    /*
     * Calcula el salario final del empleado aplicando bono y descuento.
     *
     * Lógica aritmética y jerarquía de operadores:
     *  1. Paréntesis internos: (bonoMensual * 1.10) y (salarioBase * 0.05)
     *  2. Suma: salarioBase + resultado del primer paréntesis
     *  3. Resta: resultado anterior - resultado del segundo paréntesis
     */
    public static double calcularSalarioFinal(Empleado emp) {
        // Evitamos efectos secundarios (mutabilidad innecesaria) sumando el bono solo para el cálculo
        double bonoTemporal = emp.getBonoMensual() + 1000;
        return emp.getSalarioBase() + (bonoTemporal * 1.10) - (emp.getSalarioBase() * 0.05);
    }

    /*
     * Retorna true si el ID del empleado es par.
     */
    public static boolean tieneBonoPorIdPar(Empleado emp) {
        return (emp.getIdEmpleado() % 2 == 0);
    }

    public static boolean validarElegibilidad(Empleado emp) {
        return (emp.getPuntajeTest() > 85 && emp.getEdad() < 30) || (emp.getIdSede() == 1 && !emp.isActivo());
    }

    public static String obtenerCategoriaSalarial(Empleado emp){
       return switch ((Integer) (int) emp.getSalarioBase()) {
            case Integer s when s < 500 -> "Junior";
            case Integer s when s < 1000 -> "Mid";
            default -> "Seniority";
        };
    }

    /**
     * Búsqueda instantánea usando el HashMap (O(1))
     */
    public static Empleado buscarEmpleado(int id) {
        return mapaIdEmpleados.get(String.valueOf(id));
    }

    private static List<Empleado> listaEmpleados = new ArrayList<>();
    private static Map<String, Empleado> mapaIdEmpleados = new HashMap<>();

    /**
     * Guarda un empleado 
     */
    public static void agregarEmpleado(Empleado emp) {
        listaEmpleados.add(emp);
        mapaIdEmpleados.put(String.valueOf(emp.getIdEmpleado()), emp);
    }

    /**
     * Lista todos los empleados usando var (Java 11+)
     */
    public static void listarEmpleados() {
        for (var emp : listaEmpleados) {
            System.out.println("ID: " + emp.getIdEmpleado() + " | Nombre: " + emp.getNombre() + " | Puntaje: " + emp.getPuntajeTest() + " | Salario: " + emp.getSalarioBase());
        }
    }

    /**
     * TASK 3: Sequenced Collections (Java 21)
     * Sintaxis Legacy (8/11): 
     *   - Primero: lista.get(0)
     *   - Ultimo: lista.get(lista.size() - 1)
     * Sintaxis Moderna (Java 21): 
     *   - Los nuevos métodos getFirst(), getLast() y reversed() mejoran la legibilidad 
     *     y previenen errores de índice (IndexOutOfBounds).
     */
    public static Empleado obtenerPrimerEmpleado() {
        return listaEmpleados.isEmpty() ? null : listaEmpleados.getFirst();
    }

    public static Empleado obtenerUltimoEmpleado() {
        return listaEmpleados.isEmpty() ? null : listaEmpleados.getLast();
    }

    public static List<Empleado> obtenerListaInversa() {
        // reversed() devuelve una vista en orden inverso sin copiar la lista
        return listaEmpleados.reversed();
    }

    /**
     * TASK 4: Filtrado avanzado y removeIf
     */
    public static void eliminarPorBajoPuntaje(int puntajeMinimo) {
        listaEmpleados.removeIf(emp -> emp.getPuntajeTest() < puntajeMinimo);
        // Sincronizamos el mapa
        mapaIdEmpleados.entrySet().removeIf(entry -> entry.getValue().getPuntajeTest() < puntajeMinimo);
        System.out.println("Empleados con puntaje menor a " + puntajeMinimo + " eliminados.");
    }

    /**
     * Genera un reporte final usando tipado inferido (var)
     */
    public static void generarReporteFinal() {
        var total = listaEmpleados.size();
        var sumaSalarios = 0.0;
        int conteoDevs = 0;
        int conteoGerentes = 0;
        double presupuestoTotalGerentes = 0;

        for (var emp : listaEmpleados) {
            sumaSalarios += emp.getSalarioBase();

            // REDISEÑO CON PATTERN MATCHING (Java 17+)
            if (emp instanceof Desarrollador dev) {
                conteoDevs++;
            } else if (emp instanceof Gerente ger) {
                conteoGerentes++;
                presupuestoTotalGerentes += ger.getPresupuestoMensual();
            }
        }

        var promedio = total > 0 ? sumaSalarios / total : 0.0;

        System.out.println("\n====== REPORTE FINAL (ESTILO MODERNO) ======");
        System.out.println("Total de empleados: " + total);
        System.out.println("Desarrolladores: " + conteoDevs);
        System.out.println("Gerentes: " + conteoGerentes);
        System.out.println("Presupuesto total bajo gerencia: $" + String.format("%.2f", presupuestoTotalGerentes));
        System.out.println("Promedio de salarios: $" + String.format("%.2f", promedio));
        System.out.println("============================================\n");
    }

    /**
     * Elimina un empleado de ambas colecciones por su ID
     */
    public static void eliminarEmpleado(int id) {
        // Usamos removeIf para el ArrayList (Paso 4 de la guía)
        listaEmpleados.removeIf(emp -> emp.getIdEmpleado() == id);
        // Removemos del HashMap por clave
        mapaIdEmpleados.remove(String.valueOf(id));
        System.out.println("Empleado con ID " + id + " eliminado.");
    }

    /*
     * TASK 2: Inicialización y Factory Methods (Java 9/11)
     * List.of() crea colecciones inmutables, lo cual es más eficiente en memoria 
     * y seguro en entornos multi-hilo que un ArrayList tradicional. 
     * Nota: No permiten .add() o .remove().
     */
    private static final List<String> tecnologias = List.of("Java", "Python", "React");

    private static final Map<Integer, String> sedes = Map.of(
        1, "Medellin", 
        2, "Marinilla"
    );

    public static DesempenoReport generarReporteIndividual(Empleado emp){
        double promedio = 95.5;

        String feedback = (promedio > 90) ? "Excelente desempeno" : "Buen desempeno, puede mejorar";

        //Retornamos una instancia del record
        return new DesempenoReport(emp.getIdEmpleado(), promedio, feedback);
    }

    /**
     * Ejemplo de Sintaxis Legacy (Java 8/11):
     * Requiere instanceof seguido de casting manual.
     */
    public static void mostrarHabilidadLegacy(Object obj) {
        if (obj instanceof Desarrollador) {
            Desarrollador dev = (Desarrollador) obj; // Casting manual obligatorio
            System.out.println("[LEGACY] Lenguaje: " + dev.getLenguajePrincipal());
        } else if (obj instanceof Gerente) {
            System.out.println("[LEGACY] Presupuesto: " + ((Gerente) obj).getPresupuestoMensual());
        }
    }

    /**
     * Ejemplo de Sintaxis Moderna (Java 17/21):
     * Usa Pattern Matching para evitar el casting manual.
     */
    public static void mostrarHabilidadModerna(Object obj) {
        if (obj instanceof Desarrollador dev) {
            // 'dev' ya es de tipo Desarrollador aquí dentro
            System.out.println("[MODERNO] Lenguaje: " + dev.getLenguajePrincipal());
        } else if (obj instanceof Gerente ger) {
            System.out.println("[MODERNO] Presupuesto: " + ger.getPresupuestoMensual());
        }
    }
}
