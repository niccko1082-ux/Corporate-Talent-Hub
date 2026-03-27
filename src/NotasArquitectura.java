/**
 * NotasArquitectura - Diferencias entre Java 8 y Java 17/21,
 * y cómo la JVM maneja la memoria con el Garbage Collector.
 */
public class NotasArquitectura {

    // === 1. ¿Qué cambió de Java 8 a Java 17/21? ===

    // En Java 8 todos los JARs comparten un classpath plano, cualquier clase pública
    // es visible para todos. En Java 17+ existe el sistema de módulos (JPMS), que
    // permite controlar qué se expone y qué queda interno. Esto hace las apps más
    // seguras y permite generar runtimes más pequeños con jlink.

    // A nivel de código, Java 8 obliga a escribir clases completas con getters,
    // equals y hashCode para representar datos. Desde Java 17 se pueden usar records
    // que hacen lo mismo en una sola línea. También se agregaron text blocks,
    // pattern matching y switch como expresión, que hacen el código más limpio.

    // En cuanto a concurrencia, Java 8 solo tiene hilos del sistema operativo que
    // pesan ~1 MB cada uno. Java 21 trae los Virtual Threads, que son hilos manejados
    // por la JVM mucho más livianos (~KB), permitiendo crear millones sin problema.

    // El GC por defecto también cambió: en Java 8 es el Parallel GC (enfocado en
    // rendimiento), mientras que en Java 17/21 es G1 GC (que balancea rendimiento
    // con tiempos de pausa). Java 21 además ofrece ZGC con pausas menores a 1 ms.

    // === 2. ¿Cómo maneja la JVM la memoria? ===

    // Cuando creamos un objeto con "new", la JVM lo pone en una zona del Heap llamada
    // Eden (parte de la Young Generation). Si el objeto sigue siendo usado después de
    // varias recolecciones, se mueve a la Old Generation, que se limpia con menos
    // frecuencia.

    // El Garbage Collector se encarga de liberar los objetos que ya nadie referencia.
    // Esto pasa cuando la variable sale de scope, se le asigna null o se reasigna.
    // No hay que liberar memoria manualmente como en C; el GC lo hace por nosotros.

    // La JVM también tiene trucos para optimizar la memoria:
    // - Si detecta que un objeto no sale del método donde se creó (Escape Analysis),
    //   lo pone en el stack en vez del heap, evitando pasar por el GC.
    // - Cada hilo tiene su propio espacio en Eden (TLAB) para no competir con otros.
    // - Desde Java 9, los Strings que solo usan caracteres básicos ocupan la mitad
    //   de memoria gracias a Compact Strings.
}
