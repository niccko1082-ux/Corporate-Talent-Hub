package com.startup.demos;

import com.startup.models.Empleado;
import com.startup.models.EmpresaRecord;
import com.startup.services.EmpleadoService;
import java.util.Scanner;

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
        Empleado emp1 = new Empleado(100, 200, 10000, 90, 20, 1, false);
        Empleado emp2 = new Empleado(101, 1800000, 8000, 80, 25, 2, true);

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
        EmpleadoService service = new EmpleadoService();
        emp1 = emp2; // reasignamos para continuar la demo

        // --- Demo: Menu Switch ---
        Scanner sc = new Scanner(System.in);
        int opciones;

        do {
            System.out.println("""
                    ======Menu Principal======
                    1) Calcular Salaario Final.
                    2) Verificar bono por ID par.
                    3) Validar Elegibilidad.
                    4) Mostrar Categoria Salario
                    0) Salir.
                    =========================""");

            opciones = sc.nextInt();

            switch (opciones) {
                case 1:
                    System.out.println("Salario " + service.calcularSalarioFinal(emp1));
                    break;
                case 2:
                    System.out.println("Bono par " + service.tieneBonoPorIdPar(emp1));
                    break;
                case 3:
                    System.out.println("Elegible " + service.validarElegibilidad(emp1));
                    break;
                case 4:
                    System.out.println("Categaria: " + service.obtenerCategoriaSalarial(emp1));
                default:
                    System.out.println("Opcion invalida");

            }
        } while (opciones != 0);

        /*
         * La diferencia entre usar switch legacy(Java 8) y switch modern(Java 17+)
         * es que la legacy al momento de olvidar escribir break, pasa al siguiente
         * case, ejecuta lo que tenga y
         * asi hasta encontrar un brake, lo que genera resultados inespetados. En la
         * Opcion creada para Java 17
         * en adelante, cada case al terminar, tiene su propio break sin tener que
         * ponerlo
         */

        System.out.println("Salario final:       " + service.calcularSalarioFinal(emp1));
        System.out.println("Bono por ID par:     " + service.tieneBonoPorIdPar(emp1));
        System.out.println("Elegible:            " + service.validarElegibilidad(emp1));
    }
}
