package com.startup.models;

/**
 * Modelo de datos del Empleado.
 * Solo contiene atributos que describen la entidad.
 * La lógica de negocio está en EmpleadoService.
 */
public class Empleado {

    public int idEmpleado;
    public String nombre;
    public double salarioBase;
    public double bonoMensual;
    public int puntajeTest;
    public int edad;
    public int idSede;
    public boolean esActivo;
    public String fechaNacimiento;

    public Empleado(int idEmpleado, String nombre, double salarioBase, double bonoMensual,
                    int puntajeTest, int edad, int idSede, boolean esActivo) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.salarioBase = salarioBase;
        this.bonoMensual = bonoMensual;
        this.puntajeTest = puntajeTest;
        this.edad = edad;
        this.idSede = idSede;
        this.esActivo = esActivo;
    }

    // Constructor para captura rápida (nombre, salario, fechaNacimiento)
    public Empleado(String nombre, double salarioBase, String fechaNacimiento) {
        this.nombre = nombre;
        this.salarioBase = salarioBase;
        this.fechaNacimiento = fechaNacimiento;
    }
}
