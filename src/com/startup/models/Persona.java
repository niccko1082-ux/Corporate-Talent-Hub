package com.startup.models;

// Solo los desarrolladores y gerentes pueden ser hijos de esta interfaz.

public abstract sealed class Persona permits Empleado, ConsultorExterno{

   private final String nombre;

   public Persona(String nombre) {
       this.nombre = nombre;
   }

   public String getNombre() {return nombre;}
}
