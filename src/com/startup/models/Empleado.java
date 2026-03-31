package com.startup.models;

/**
 * Modelo de datos del Empleado.
 * Solo contiene atributos que describen la entidad.
 * La lógica de negocio está en EmpleadoService.
 */
public class Empleado {

    public int idEmpleado;
    public double salarioBase;
    public double bonoMensual;
    public int puntajeTest;
    public int edad;
    public int idSede;
    public boolean esActivo;

    public Empleado(int idEmpleado, double salarioBase, double bonoMensual,
                    int puntajeTest, int edad, int idSede, boolean esActivo) {
        this.idEmpleado = idEmpleado;
        this.salarioBase = salarioBase;
        this.bonoMensual = bonoMensual;
        this.puntajeTest = puntajeTest;
        this.edad = edad;
        this.idSede = idSede;
        this.esActivo = esActivo;
    }
}
