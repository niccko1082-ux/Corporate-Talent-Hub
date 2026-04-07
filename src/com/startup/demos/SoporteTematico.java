package com.startup.demos;

import com.startup.models.Empleado;
import com.startup.services.EmpleadoService;

import java.util.Scanner;

public class SoporteTematico {

    public static void ejecutar() {

        var teclado = new Scanner(System.in); // Java 11+
        var opcion  = 0;

        do {
            System.out.println("""
                        ═══ SOPORTE TEMÁTICO ═══
                        1) Capturar Empleado
                        2) Validar byte
                        3) Validar short
                        4) Validar int
                        5) Validar float / double
                        6) Validar char
                        7) Validar boolean
                        8) Matriz de Desempeño
                        0) Volver
                    """);

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                // ─────────────────────────────────────────────────────────────
                case 1 -> {
                    System.out.println("Ingrese el nombre del empleado:");
                    var nombre = teclado.nextLine();

                    System.out.println("Ingrese el salario del empleado:");
                    var salario = teclado.nextDouble();
                    teclado.nextLine();

                    System.out.println("Ingrese la fecha de nacimiento (YYYY-MM-DD):");
                    var fechaNacimiento = teclado.nextLine();

                    if (nombre.isBlank()) {
                        System.out.println("El nombre no puede estar vacío.");
                    } else if (salario <= 0) {
                        System.out.println("El salario debe ser mayor a 0.");
                    } else if (fechaNacimiento.isBlank()) {
                        System.out.println("La fecha de nacimiento no puede estar vacía.");
                    } else {
                        var empleado = new Empleado(nombre, salario, fechaNacimiento);
                        EmpleadoService.guardar(empleado);
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 2 -> {
                    System.out.println("Ingrese un número para validar como byte [ " + Byte.MIN_VALUE + " , " + Byte.MAX_VALUE + " ]:");
                    var inputByte = teclado.nextLong();
                    teclado.nextLine();

                    if (inputByte >= Byte.MIN_VALUE && inputByte <= Byte.MAX_VALUE) {
                        System.out.println("Válido  →  byte: " + (byte) inputByte);
                    } else {
                        System.out.println("Inválido  →  " + inputByte + " está fuera del rango byte.");
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 3 -> {
                    System.out.println("Ingrese un número para validar como short [ " + Short.MIN_VALUE + " , " + Short.MAX_VALUE + " ]:");
                    var inputShort = teclado.nextLong();
                    teclado.nextLine();

                    if (inputShort >= Short.MIN_VALUE && inputShort <= Short.MAX_VALUE) {
                        System.out.println("Válido  →  short: " + (short) inputShort);
                    } else {
                        System.out.println("Inválido  →  " + inputShort + " está fuera del rango short.");
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 4 -> {
                    System.out.println("Ingrese un número para validar como int [ " + Integer.MIN_VALUE + " , " + Integer.MAX_VALUE + " ]:");
                    var inputInt = teclado.nextLong();
                    teclado.nextLine();

                    if (inputInt >= Integer.MIN_VALUE && inputInt <= Integer.MAX_VALUE) {
                        System.out.println("Válido  →  int: " + (int) inputInt);
                    } else {
                        System.out.println("Inválido  →  " + inputInt + " está fuera del rango int.");
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 5 -> {
                    System.out.println("Ingrese un número decimal para validar como float / double:");
                    var inputDouble = teclado.nextDouble();
                    teclado.nextLine();

                    if (Double.isNaN(inputDouble) || Double.isInfinite(inputDouble)) {
                        System.out.println("Inválido  →  el valor no es un decimal representable.");
                    } else {
                        System.out.println("Válido  →  double: " + inputDouble);
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 6 -> {
                    System.out.println("Ingrese un texto para validar como char (debe ser exactamente 1 carácter):");
                    var inputChar = teclado.nextLine();

                    if (inputChar.length() == 1) {
                        System.out.println("Válido  →  char: '" + inputChar.charAt(0) + "'");
                    } else {
                        System.out.println("Inválido  →  un char debe tener exactamente 1 carácter.");
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 7 -> {
                    System.out.println("Ingrese un valor para validar como boolean (true / false):");
                    var inputBool = teclado.nextLine().trim().toLowerCase();

                    if (inputBool.equals("true") || inputBool.equals("false")) {
                        System.out.println("Válido  →  boolean: " + Boolean.parseBoolean(inputBool));
                    } else {
                        System.out.println("Inválido  →  un boolean solo acepta \"true\" o \"false\".");
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 8 -> MatrizDesempeno.ejecutar();

                // ─────────────────────────────────────────────────────────────
                case 0 -> System.out.println("Saliendo...");

                default  -> System.out.println("Opción no válida, intente de nuevo.");
            }

        } while (opcion != 0);

        teclado.close();
    }
}
