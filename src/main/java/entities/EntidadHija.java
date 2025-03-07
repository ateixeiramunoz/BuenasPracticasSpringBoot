package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una clase de entidad persistente de base de datos denominada "EntidadHija".
 *
 * Anotaciones de clase:
 * - {@code @Getter}: Genera automáticamente los métodos 'getter' para todos los campos de la clase.
 *   Proviene de la biblioteca Lombok. Útil para reducir código repetitivo.
 *
 * - {@code @Setter}: Genera automáticamente los métodos 'setter' para todos los campos de la clase.
 *   Proviene de la biblioteca Lombok. También forma parte de la simplificación del código.
 *
 * - {@code @Entity}: Indica que la clase está mapeada como una tabla en la base de datos.
 *   Es necesaria para que Hibernate o JPA reconozcan esta clase como una entidad persistente.
 *   Esta clase se asignará a una tabla de base de datos con las columnas correspondientes a los atributos declarados.
 *
 * Campos:
 * - {@code id}: Identificador único para cada instancia de `EntidadHija`. Es la clave primaria de la base de datos.
 * - {@code nombre}: Representa el nombre de la entidad hija.
 * - {@code entidadPadre}: Establece una asociación muchos-a-uno (Many-to-One) con la entidad `EntidadPadre`.
 *   Esto significa que múltiples instancias de `EntidadHija` pueden estar relacionadas con una única instancia de `EntidadPadre`.
 */
@Getter
@Setter
@Entity
public class EntidadHija  {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String nombre;

    @ManyToOne
    private EntidadPadre entidadPadre;

}