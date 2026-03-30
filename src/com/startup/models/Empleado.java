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


    public static void main (String[] args){
        System.out.println("""
                Encabezado del sistema""");

        Empleado emp1 = new Empleado();
        Empleado emp2 = new Empleado();

        EmpresaRecord miEmpresa = new EmpresaRecord("Mi Nueva Empresa", 9000123, "2005-09-12");

        emp1 = null;

        try{
            System.out.println("Longitud del string: " + emp1.length());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        /*
        * En Java 8 el NullPointerException simplemente indicaba que habia
        *  un error, pero no decia donde se encontrava. Ahora el Helpful
        * le indica que tiene el error y te muestra exactamente donde
        *  esta, Lo que facilita la busqueda del error y se optimiza mas
        * tiempo
        */

        /*
        * Comparar dos objetos(A, B) con (==) lo que se hace internamente
        *  es coparar si la direccion de memoria que tiene A es la misma
        * de B. Esto sucede debido a que el contenido de A se guarda en un
        *  espacio de memoria (HEAP) la cual esta refereciada por el
        * nombre que esta en (STACK). Aca es donde al comparar se genera
        *  un error de referencia ya que B tiene una referencia en (HEAP)
        *  diferente a la de A
        */

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

        int IdEmpleado = 100;
        double salarioBase = 2000000;
        double bonoMensual = 10000;
        int puntaheTest = 90;
        int edad = 20;
        int idSede = 1;
        boolean esActivo = false;

        /*
        * Logica aritmetica y jerarquia de operadores.
        * 1. Solucion de parentesis internos : (bonoMensual * 1.10) y (salariobase * 0.05)
        * 2. Suma : salarioBase + resultado del primer parentesis
        * 3. Resta : resultado anterior - resultado del segundo parentesis
        */



    public double calcularSalarioFinal(){
        this.bonoMensual += 1000;

        double salarioFinal = (salarioBase + (bonoMensual * 1.10) - (salarioBase * 0.05));
        return salarioFinal;
    }

    public boolean tieneBonoPorIdPar(){
        return (IdEmpleado % 2 == 0);
    }

    public boolean validarElegibilidad(){
        return (puntaheTest > 85 && edad < 30) || (idSede == 1 && !esActivo);
    }
}
