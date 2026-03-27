package com.startup.models;

public class Empleado {

    // Datos primitivos
    byte n1 = 120;
    short n2 = 30000;
    int n3 = 900000000;
    long n4 = 1000000000000000000l;
    float n5 = 20.15f;
    double n6 = 20.15;
    char l1 = 'n';
    boolean isTrue = true;
    String str = "nicolas";

    public static void main (String[] args){
        System.out.println("""
                Encabezado del sistema""");
    }


    public record EmpresaRecord(String nombre, int nit, String fechaFundacion) {

    }

    /*
    En Java 8, para representar un simple contenedor de datos (POJO), estamos obligados a escribir (o generar con el IDE) entre 40 y 60 líneas de
    código. Esto incluye el constructor, los getters, equals, hashCode y toString. A este código se le conoce como boilerplate (código repetitivo) y
    tiene un costo: es más difícil de leer, más propenso a errores si olvidas actualizar un método al añadir un campo, y oculta la verdadera intención
    el modelo.

    En Java 17/21, el Record reduce todo eso a 1 sola línea. La intención es clara: "esta es una estructura de datos y nada más".

    La inmutabilidad de un record en Java (Java 17+), hace rerecencia a que los datos que fueron asignados en sus campos no pueden ser modifcados,
    cada uno de sus campos son declarados directamente como private final. Es usualmente usado para modelar datos, en caso de igresar un nuevo dato
    se tendria que usan una nueva instacia.
    */
}
