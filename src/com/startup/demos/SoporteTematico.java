package com.startup.demos;

import com.startup.models.Empleado;
import com.startup.services.EmpleadoService;
import com.startup.util.InputUtils;

public class SoporteTematico {

    public static void ejecutar() {
        var opcion = 0;

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

            opcion = InputUtils.leerEntero("Seleccione una opción");

            switch (opcion) {

                // ─────────────────────────────────────────────────────────────
                case 1 -> {
                    var nombre         = InputUtils.leerString("Nombre del empleado");
                    var salario        = InputUtils.leerDouble("Salario del empleado");
                    var fechaNacimient = InputUtils.leerString("Fecha de nacimiento (YYYY-MM-DD)");

                    if (nombre.isBlank()) {
                        System.out.println("El nombre no puede estar vacío.");
                    } else if (salario <= 0) {
                        System.out.println("El salario debe ser mayor a 0.");
                    } else if (fechaNacimient.isBlank()) {
                        System.out.println("La fecha de nacimiento no puede estar vacía.");
                    } else {
                        var empleado = new Empleado(nombre, salario, fechaNacimient);
                        EmpleadoService.agregarEmpleado(empleado);
                        System.out.println("Empleado registrado correctamente.");
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 2 -> {
                    System.out.println("Rango byte: [" + Byte.MIN_VALUE + " , " + Byte.MAX_VALUE + "]");
                    var inputByte = (long) InputUtils.leerEntero("Ingrese un número");

                    if (inputByte >= Byte.MIN_VALUE && inputByte <= Byte.MAX_VALUE) {
                        System.out.println("Válido  →  byte: " + (byte) inputByte);
                    } else {
                        System.out.println("Inválido  →  " + inputByte + " está fuera del rango byte.");
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 3 -> {
                    System.out.println("Rango short: [" + Short.MIN_VALUE + " , " + Short.MAX_VALUE + "]");
                    var inputShort = (long) InputUtils.leerEntero("Ingrese un número");

                    if (inputShort >= Short.MIN_VALUE && inputShort <= Short.MAX_VALUE) {
                        System.out.println("Válido  →  short: " + (short) inputShort);
                    } else {
                        System.out.println("Inválido  →  " + inputShort + " está fuera del rango short.");
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 4 -> {
                    System.out.println("Rango int: [" + Integer.MIN_VALUE + " , " + Integer.MAX_VALUE + "]");
                    var inputInt = (long) InputUtils.leerEntero("Ingrese un número");

                    if (inputInt >= Integer.MIN_VALUE && inputInt <= Integer.MAX_VALUE) {
                        System.out.println("Válido  →  int: " + (int) inputInt);
                    } else {
                        System.out.println("Inválido  →  " + inputInt + " está fuera del rango int.");
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 5 -> {
                    var inputDouble = InputUtils.leerDouble("Ingrese un número decimal (float / double)");

                    if (Double.isNaN(inputDouble) || Double.isInfinite(inputDouble)) {
                        System.out.println("Inválido  →  el valor no es un decimal representable.");
                    } else {
                        System.out.println("Válido  →  double: " + inputDouble);
                        System.out.println("Como float: " + (float) inputDouble.doubleValue());
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 6 -> {
                    var inputChar = InputUtils.leerString("Ingrese un texto para validar como char (1 carácter)");

                    if (inputChar.length() == 1) {
                        System.out.println("Válido  →  char: '" + inputChar.charAt(0) + "'");
                    } else {
                        System.out.println("Inválido  →  un char debe tener exactamente 1 carácter.");
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 7 -> {
                    var inputBool = InputUtils.leerString("Ingrese un valor boolean (true / false)").trim().toLowerCase();

                    if (inputBool.equals("true") || inputBool.equals("false")) {
                        System.out.println("Válido  →  boolean: " + Boolean.parseBoolean(inputBool));
                    } else {
                        System.out.println("Inválido  →  un boolean solo acepta \"true\" o \"false\".");
                    }
                }

                // ─────────────────────────────────────────────────────────────
                case 8 -> MatrizDesempeno.ejecutar();

                // ─────────────────────────────────────────────────────────────
                case 0 -> System.out.println("Volviendo al menú principal...");

                default -> System.out.println("Opción no válida, intente de nuevo.");
            }

        } while (opcion != 0);
    }
}
