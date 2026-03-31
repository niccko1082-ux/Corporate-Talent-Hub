package com.startup.models;

/**
 * Modelo inmutable de Empresa usando Java Records (Java 16+).
 *
 * En Java 8, representar un contenedor de datos (POJO) requería entre 40 y 60 líneas:
 * constructor, getters, equals, hashCode y toString (boilerplate).
 *
 * En Java 17/21, el Record reduce todo eso a 1 sola línea.
 * La intención es clara: "esta es una estructura de datos y nada más".
 *
 * Inmutabilidad: los campos son declarados internamente como private final,
 * por lo que no pueden modificarse tras la construcción. Si se necesita
 * un nuevo dato, se debe crear una nueva instancia.
 */
public record EmpresaRecord(String nombre, int nit, String fechaFundacion) {

}
