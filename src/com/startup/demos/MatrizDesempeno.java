package com.startup.demos;

import java.util.Scanner;

/**
 * Matrices de desempeño y casting.
 *
 * Procesa datos multidimensionales de rendimiento de los Coders usando una
 * matriz {@code double[][]} donde:
 *   - Cada fila  = un empleado registrado.
 *   - Cada columna = la calificación de un trimestre (T1, T2, T3).
 *
 * Flujo:
 *   1. Capturar calificaciones con Scanner.
 *   2. Recorrer la matriz con for anidados para calcular promedios.
 *   3. Casting explícito (double → int) para generar un "Puntaje Simplificado",
 *      documentando la pérdida de precisión.
 */
public class MatrizDesempeno {

    // Nombres de los empleados registrados (simulando los Coders del sistema)
    private static final String[] NOMBRES = { "Carlos", "María", "Andrés" };
    private static final int TRIMESTRES = 3;

    public static void ejecutar() {

        var teclado    = new Scanner(System.in);
        var empleados  = NOMBRES.length;
        var matriz     = new double[empleados][TRIMESTRES];

        // 1. Capturar calificaciones (0.0 – 100.0) por empleado y trimestre
        System.out.println("""
                    ═══ MATRIZ DE DESEMPEÑO ═══
                    Ingrese las calificaciones (0 – 100) de cada Coder
                    para los 3 trimestres del año.
                """);

        for (int i = 0; i < empleados; i++) {
            System.out.println("── Coder: " + NOMBRES[i] + " ──");

            for (int j = 0; j < TRIMESTRES; j++) {
                System.out.print("  Trimestre " + (j + 1) + ": ");
                var calificacion = teclado.nextDouble();

                // Validar rango con if / else
                if (calificacion < 0 || calificacion > 100) {
                    System.out.println("La calificación debe estar entre 0 y 100. Se asignará 0.");
                    matriz[i][j] = 0;
                } else {
                    matriz[i][j] = calificacion;
                }
            }
            System.out.println();
        }
        teclado.nextLine(); // limpiar buffer

        // 2. Recorrer la matriz con for anidados → calcular promedios
        System.out.println("═══ REPORTE DE DESEMPEÑO ═══");
        System.out.printf("%-12s | %6s | %6s | %6s | %10s | %10s%n",
                "Coder", "T1", "T2", "T3", "Promedio", "Puntaje (int)");
        System.out.println("─".repeat(68));

        var promedios = new double[empleados];

        for (int i = 0; i < empleados; i++) {
            var suma = 0.0;

            for (int j = 0; j < TRIMESTRES; j++) {
                suma += matriz[i][j];
            }

            promedios[i] = suma / TRIMESTRES;

            /*
             * Casting explícito: double → int
             *
             * Al hacer (int) promedio se TRUNCA la parte decimal, es decir,
             * se descarta todo lo que está después del punto. Esto genera una
             * pérdida de precisión: por ejemplo, un promedio de 87.67 se
             * convierte en 87 (no se redondea, solo se trunca).
             *
             * Se utiliza este casting para generar un "Puntaje Simplificado"
             * destinado a reportes donde solo se necesitan valores enteros.
             */
            int puntajeSimplificado = (int) promedios[i];

            System.out.printf("%-12s | %6.1f | %6.1f | %6.1f | %10.2f | %10d%n",
                    NOMBRES[i],
                    matriz[i][0], matriz[i][1], matriz[i][2],
                    promedios[i],
                    puntajeSimplificado);
        }

        System.out.println("─".repeat(68));

        // 3. Documentar la pérdida de precisión del casting
       
        System.out.println("\n═══ DETALLE DE CASTING (double → int) ═══");

        for (int i = 0; i < empleados; i++) {
            int puntajeInt       = (int) promedios[i];
            double perdida       = promedios[i] - puntajeInt;

            System.out.printf("  %s:  promedio = %.2f  →  (int) = %d  →  pérdida de precisión = %.2f%n",
                    NOMBRES[i], promedios[i], puntajeInt, perdida);
        }

        System.out.println();
    }
}
