package com.startup.models;

public final class ConsultorExterno extends Persona {
    private final String empresaConsultora;

    public ConsultorExterno(String nombre, String empresaConsultora){
        super(nombre);
        this.empresaConsultora = empresaConsultora;
    }
}
