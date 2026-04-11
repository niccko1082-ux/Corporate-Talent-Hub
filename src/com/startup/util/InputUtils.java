package com.startup.util;

import java.util.Scanner;

public class InputUtils {

    private static final Scanner scanner = new Scanner(System.in);

    private InputUtils(){};

    public static int leerEntero(String mensaje){
        
        while(true){
            try {
                System.out.print(mensaje + ": ");
                String entrada = scanner.nextLine();

                return Integer.parseInt(entrada); 
            } catch (NumberFormatException e) {
                System.out.println("Error, debes ingresar un numero entero valido");
            }
        }
    }

    public static String leerString(String mensaje){
        System.out.print(mensaje + ": ");
        String entrada = scanner.nextLine();
        return entrada;
    }

    public static Double leerDouble(String mensaje){
        while(true){
            try {
                System.out.print(mensaje + ": ");
                String entrada = scanner.nextLine();

                return Double.parseDouble(entrada);
    
            } catch (NumberFormatException e) {
                System.out.println("Error, debes ingresar un numero decimal valido");
            }

        }
    }
}
