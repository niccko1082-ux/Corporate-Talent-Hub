package com.startup.demos;

import java.util.InputMismatchException;
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
                
                double calificacion = 0;
                
                try {
                    calificacion = teclado.nextDouble();
                } catch (InputMismatchException e) {
                    /*
                     * Análisis LTS (Java 8 vs Java 17/21) - Diagnóstico detallado:
                     * En Java 8, los mensajes trazados en excepciones solían ser muy opacos. 
                     * Por ejemplo, un NullPointerException solo señalaba la línea, sin 
                     * identificar la variable involucrada.
                     * A partir de Java 14 (y siendo el estándar en LTS Java 17/21), 
                     * los "Helpful NullPointerExceptions" detallan exactamente qué variable o 
                     * método originó el fallo, mejorando radicalmente la observabilidad 
                     * y el diagnóstico de la máquina virtual moderna.
                     */
                    System.out.println("  ⚠ InputMismatchException capturada: No ingresaste un formato numérico válido.");
                    teclado.nextLine(); // Limpiamos el token inválido del buffer
                    calificacion = -1;  // Forzamos el valor a salir del rango [0, 100]
                }

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
        System.out.printf("%-12s | %6s | %6s | %6s | %10s | %10s | %10s%n",
                "Coder", "T1", "T2", "T3", "Promedio", "Puntaje", "Promoción");
        System.out.println("─".repeat(80));

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

            // ── Uso de Operador Ternario para decidir el estado de promoción ──
            var estadoPromocion = (promedios[i] >= 80.0) ? "Aprobado" : "Rechazado";

            System.out.printf("%-12s | %6.1f | %6.1f | %6.1f | %10.2f | %10d | %10s%n",
                    NOMBRES[i],
                    matriz[i][0], matriz[i][1], matriz[i][2],
                    promedios[i],
                    puntajeSimplificado,
                    estadoPromocion);
        }

        System.out.println("─".repeat(80));

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
