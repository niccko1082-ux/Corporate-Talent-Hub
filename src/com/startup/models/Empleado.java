package com.startup.models;

/**
 * ¿Por qué Sealed Classes para el diseño de APIs?
 * 
 * Al usar 'sealed' en lugar de herencia abierta (open inheritance), garantizamos:
 * 1. Control de Jerarquía: El diseñador de la API decide exactamente quién puede extender la clase,
 *    evitando que usuarios externos creen subtipos no autorizados que rompan la lógica del sistema.
 * 2. Exhaustividad (Exhaustiveness): Permite al compilador verificar que todas las variantes han sido 
 *    manejadas en estructuras como switch expressions, eliminando la necesidad de bloques 'default'.
 * 3. Seguridad de Diseño: Evita problemas de acoplamiento accidental y garantiza que el 
 *    comportamiento de la jerarquía sea predecible y finito.
 */
public abstract sealed class Empleado extends Persona permits Desarrollador, Gerente {
    private final int idEmpleado;
    private double salarioBase;
    private double bonoMensual;
    private int puntajeTest;
    private int edad;
    private int idSede;
    private boolean esActivo;

    public Empleado(int idEmpleado, String nombre, double salarioBase, double bonoMensual, int puntajeTest, int edad, int idSede, boolean esActivo){
        super(nombre);
        this.idEmpleado = idEmpleado;
        this.salarioBase = salarioBase;
        this.bonoMensual = bonoMensual;
        this.puntajeTest = puntajeTest;
        this.edad = edad;
        this.idSede = idSede;
        this.esActivo = esActivo;
    }

    public int getIdEmpleado() { return idEmpleado; }
    public double getSalarioBase() { return salarioBase; }
    public double getBonoMensual() { return bonoMensual; }
    public int getPuntajeTest() { return puntajeTest; }
    public int getEdad() { return edad; }
    public int getIdSede() { return idSede; }
    public boolean isActivo() { return esActivo; }

    public abstract double calcularSalario();
}
