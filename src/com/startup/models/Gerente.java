package com.startup.models;

public final class Gerente extends Empleado implements Promocionable {
    private final double presupuestoMensual;

    public Gerente(int idEmpleado, String nombre, double salarioBase, double presupuestoMensual) {
        super(idEmpleado, nombre, salarioBase, 1000.0, 95, 30, 2, true);
        this.presupuestoMensual = presupuestoMensual;
    }

    @Override
    public double calcularSalario(){
        return getSalarioBase() * 1.25;
    }

    @Override
    public double calcularBonoAscenso() {
        return getSalarioBase() * 0.20 + 500; // 20% + bono fijo para gerentes
    }

    public double getPresupuestoMensual() { return presupuestoMensual; }
}