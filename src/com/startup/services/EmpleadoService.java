package com.startup.services;

import com.startup.models.Empleado;

public class EmpleadoService {

    /*
     * Calcula el salario final del empleado aplicando bono y descuento.
     *
     * Lógica aritmética y jerarquía de operadores:
     *  1. Paréntesis internos: (bonoMensual * 1.10) y (salarioBase * 0.05)
     *  2. Suma: salarioBase + resultado del primer paréntesis
     *  3. Resta: resultado anterior - resultado del segundo paréntesis
     */
    public double calcularSalarioFinal(Empleado emp) {
        emp.bonoMensual += 1000;
        return emp.salarioBase + (emp.bonoMensual * 1.10) - (emp.salarioBase * 0.05);
    }

    /*
     * Retorna true si el ID del empleado es par.
     */
    public boolean tieneBonoPorIdPar(Empleado emp) {
        return (emp.idEmpleado % 2 == 0);
    }

    public boolean validarElegibilidad(Empleado emp) {
        return (emp.puntajeTest > 85 && emp.edad < 30) || (emp.idSede == 1 && !emp.esActivo);
    }
}
