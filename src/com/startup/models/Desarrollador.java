package com.startup.models;

public final class Desarrollador extends Empleado implements Promocionable {
    private final String lenguajePrincipal;

    public Desarrollador(int idEmpleado, String nombre, double salarioBase, String lenguajePrincipal) {
        super(idEmpleado, nombre, salarioBase, 500.0, 90, 25, 1, true);
        this.lenguajePrincipal = lenguajePrincipal;
    }

    @Override
    public double calcularSalario() {
        return getSalarioBase() * 1.15;
    }

    @Override
    public double calcularBonoAscenso() {
        return getSalarioBase() * 0.10; // 10% de bono para desarrolladores
    }

    public String getLenguajePrincipal() { return lenguajePrincipal; }
}