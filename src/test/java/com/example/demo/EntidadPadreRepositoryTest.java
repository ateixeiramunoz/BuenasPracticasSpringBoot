package com.example.demo;

import com.example.demo.entities.EntidadHija;
import com.example.demo.entities.EntidadPadre;
import com.example.demo.repositories.EntidadPadreRepository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;

import org.testcontainers.containers.MySQLContainer;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Clase de prueba unitaria para el repositorio de la entidad EntidadPadre.
 * Esta clase implementa varios métodos de prueba para garantizar la correcta funcionalidad
 * del repositorio y la persistencia de datos en una base de datos relacional, utilizando
 * Testcontainers para configurar un contenedor de base de datos MySQL.
 *
 * <p>Las pruebas incluidas aseguran el correcto comportamiento de las operaciones CRUD y las
 * relaciones entre entidades.
 *
 * <p><strong>Anotaciones utilizadas:</strong>
 * <ul>
 *   <li>{@code @Log4j2}:
 *       Proporcionada por Lombok, habilita el registro de logs mediante la API de Log4j2, permitiendo
 *       escribir mensajes de log fácilmente desde esta clase.</li>
 *   <li>{@code @DataJpaTest}:
 *       Una anotación de Spring Boot para configurar de manera automática un entorno de prueba centrado
 *       en JPA. Configura un contexto de prueba que incluye DataSource, EntityManager y todos los
 *       repositorios relacionados, pero sin cargar componentes web u otros servicios innecesarios.</li>
 *   <li>{@code @Testcontainers}:
 *       Específica de la biblioteca Testcontainers, habilita el uso de contenedores de Docker
 *       administrados para las pruebas, proporcionando un conjunto confiable de recursos externos
 *       como bases de datos o mensajes en colas para pruebas de integración.</li>
 *   <li>{@code @Autowired}:
 *       Una anotación de Spring que inyecta automáticamente las dependencias necesarias, en este
 *       caso, permite inyectar una instancia del repositorio EntidadPadreRepository.</li>
 *   <li>{@code @Container}:
 *       Proporcionada por Testcontainers, define un contenedor Docker que se inicializará
 *       automáticamente para pruebas. En este caso, se utiliza para configurar un contenedor MySQL.</li>
 *   <li>{@code @ServiceConnection}:
 *       Configuración específica de Spring Boot que permite obtener automáticamente la conexión
 *       configurada desde el contenedor Testcontainers para usarse en pruebas.</li>
 *   <li>{@code @Test}:
 *       Indicador de un método de prueba en JUnit. Permite marcar métodos que deben ejecutarse
 *       como casos de prueba.</li>
 *   <li>{@code @Order}:
 *       Anotación de JUnit 5 para especificar el orden secuencial de ejecución de los métodos
 *       de prueba dentro de la clase. Es útil en entornos donde el orden de las pruebas puede
 *       afectar resultados.</li>
 * </ul>
 */
@Log4j2
@DataJpaTest
@Testcontainers
class EntidadPadreRepositoryTest {

    @Autowired
    EntidadPadreRepository entidadPadreRepository;

    /**
     * Contenedor estático de MySQL para pruebas y desarrollo con Testcontainers.
     *
     * Esta clase define un contenedor de MySQL utilizando la biblioteca Testcontainers,
     * que permite la emulación de una base de datos MySQL en un entorno de prueba.
     * El contenedor incluye configuración para la base de datos, el usuario,
     * y la contraseña, simulando un entorno realista para garantizar que las pruebas
     * se ejecuten en condiciones controladas.
     *
     * Anotaciones de clase:
     *
     * @Container
     * Indica que la instancia de este contenedor debe manejarse por Testcontainers
     * y debe estar activa durante el ciclo de vida del test asociado. Esto asegura
     * que el contenedor sea iniciado antes y detenido después de las pruebas automáticas.
     *
     * @ServiceConnection
     * Declara que este contenedor actuará como un servicio conectado para la aplicación
     * Spring. Esta anotación permite que Spring Boot integre automáticamente este contenedor
     * con la configuración de contexto de prueba, facilitando el acceso al contenedor
     * como si fuera un servicio real en producción.
     *
     * Atributos configurados:
     * - La imagen específica de MySQL utilizada es "mysql:8.0.32".
     * - Se establece el nombre de la base de datos como "testDB".
     * - Se define el usuario de la base de datos como "appuser".
     * - Una contraseña segura, "password123", se asigna al usuario.
     */
    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(
            "mysql:latest"
    ).withDatabaseName("testDB").withUsername("appuser").withPassword("password123");

    /**
     * Método de prueba para verificar la correcta carga del contexto de la aplicación Spring.
     * <p>
     * Este método se asegura de que los componentes y recursos principales necesarios
     * para el correcto funcionamiento de la aplicación estén inicializados y no sean nulos.
     * Además, imprime información de debug sobre el estado de la base de datos
     * utilizada durante las pruebas, como el nombre de la base de datos, el nombre
     * de usuario y la contraseña, a través del registro.
     * </p>
     *
     * Funcionalidad del método:
     * - Comprueba que el `entidadPadreRepository` no sea nulo, lo cual valida que
     *   el bean relacionado ha sido correctamente inyectado por el contenedor
     *   de Spring.
     * - Verifica que el contenedor `mySQLContainer` esté inicializado, lo cual
     *   asegura la existencia de un ambiente de pruebas para operar la base de datos.
     * - Recorre una lista de cadenas y registra información útil para propósitos de
     *   depuración relacionados con la base de datos de prueba.
     *
     * Consideraciones:
     * - Este método utiliza aserciones para validar componentes clave, las cuales
     *   son esenciales para detectar fallos en la configuración.
     * - Los mensajes de depuración son registrados como errores (`log.error`), lo cual
     *   podría interpretarse como un estilo de manejo informacional para destacar
     *   la importancia de los mensajes en un entorno de pruebas.
     *
     * Anotaciones explicadas:
     * <ul>
     * <li><b>@Test</b>: Identifica que este método es un caso de prueba que será
     * ejecutado por el framework de pruebas. Esta anotación proviene de JUnit
     * e indica que el contenido del método se utilizará para validar cierto
     * comportamiento o estado de la aplicación.</li>
     *
     * <li><b>@Order(1)</b>: Especifica el orden en que este caso de prueba debe
     * ser ejecutado en relación con otros casos de prueba dentro del mismo
     * contexto. En este caso, el valor `1` indica que este será el primer
     * método de prueba en la secuencia.</li>
     * </ul>
     */
    @Test
    @Order(1)
    void contextLoads() {
        log.info("Iniciando la prueba contextLoads...");
        assertThat(entidadPadreRepository).isNotNull();
        assertThat(mySQLContainer).isNotNull();
        for (String s : Arrays.asList("Context loaded", "Database name: " + mySQLContainer.getDatabaseName(), "Username: " + mySQLContainer.getUsername(), "Password: " + mySQLContainer.getPassword())) {
            log.error(s);
        }
        log.info("Contexto de Spring cargado con éxito.");
    }


    /**
     * Prueba unitaria que verifica la funcionalidad de guardar una nueva entidad padre
     * en el repositorio asociado. Este método realiza lo siguiente:
     *
     * <ul>
     *   <li>Crea una instancia de la clase EntidadPadre.</li>
     *   <li>Asigna un valor a uno de sus campos, en este caso el nombre.</li>
     *   <li>Guarda la nueva instancia en el repositorio utilizando el método save().</li>
     *   <li>Recupera el identificador de la entidad guardada y verifica que no sea nulo,
     *       validando que el proceso de guardado fue exitoso.</li>
     * </ul>
     *
     * Este método garantiza que la persistencia de datos en el repositorio
     * funciona correctamente para la entidad padre.
     *
     * Anotaciones utilizadas:
     *
     * <ul>
     *   <li>{@code @Test}: Indica que este método es un caso de prueba que debe ser
     *       ejecutado por el framework de testing (habitualmente JUnit en el ecosistema Spring).</li>
     *   <li>{@code @Order(2)}: Especifica el orden de ejecución de este test en relación
     *       con otros en la misma clase. En este caso, el método se ejecutará en segundo lugar.</li>
     * </ul>
     *
     * Funcionalidad del método:
     * Este método asegura que el repositorio permite guardar la entidad correctamente,
     * asignándole un identificador único. También utiliza una aserción para garantizar
     * que la operación de persistencia no produce errores, validando que el ID generado
     * no sea nulo tras la operación.
     *
     */
    @Test
    @Order(2)
    void guardarEntidadPadre() {
        log.info("Iniciando la prueba guardarEntidadPadre...");
        var entidadPadre = new EntidadPadre();
        entidadPadre.setNombre("JetBrains");
        log.debug("Creada EntidadPadre con nombre {}", entidadPadre.getNombre());
        log.debug("Creada EntidadPadre con nombre {}", entidadPadre.getNombre());
        int entidadPadreId = entidadPadreRepository.save(entidadPadre).getId();
        log.debug("EntidadPadre guardada con ID {}", entidadPadreId);
        assertThat(entidadPadreId).isPositive();
    }

    /**
     * Método de prueba para verificar la funcionalidad de encontrar una entidad padre por su ID
     * utilizando el repositorio correspondiente.
     *
     * Este método evalúa los siguientes aspectos:
     * - La capacidad de guardar una entidad padre utilizando el repositorio.
     * - El correcto uso del método `findById` para recuperar una entidad basada en su identificador.
     * - La validación de los datos recuperados, asegurando que el nombre de la entidad guardada
     *   y encontrada coincida.
     *
     * Funcionamiento del método:
     * 1. Se crea una instancia de la clase `EntidadPadre`.
     * 2. Se asigna el nombre "JetBrains" a la entidad padre.
     * 3. Se guarda la entidad padre en la base de datos mediante el repositorio `entidadPadreRepository`.
     *    El ID generado al guardar la entidad se almacena.
     * 4. Se recupera la entidad padre de la base de datos utilizando el ID previamente guardado por
     *    medio del método `findById`.
     * 5. Se lanza una excepción si no se encuentra la entidad.
     * 6. Finalmente, se realiza una afirmación para verificar que el nombre de la entidad recuperada
     *    coincide con el nombre asignado inicialmente.
     *
     */
    @Test
    @Order(3)
    void encontrarEntidadPadreById() {
        log.debug("Iniciando la prueba encontrarEntidadPadreById...");
        var entidadPadre = new EntidadPadre();
        entidadPadre.setNombre("JetBrains");
        int entidadPadreId = entidadPadreRepository.save(entidadPadre).getId();
        log.debug("EntidadPadre guardada con ID {}", entidadPadreId);
        var entidadPadre1 = entidadPadreRepository.findById(entidadPadreId).orElseThrow();
        log.debug("EntidadPadre recuperada con ID {}", entidadPadre1.getId());
        assertThat(entidadPadre1.getNombre()).isEqualTo("JetBrains");
    }

    /**
     * Método de prueba para verificar la funcionalidad de búsqueda de una entidad padre
     * por su nombre en el repositorio correspondiente.
     *
     * Este método realiza lo siguiente:
     * 1. Crea una nueva instancia de `EntidadPadre` y establece su propiedad `nombre`.
     * 2. Guarda la entidad creada en el repositorio y obtiene su ID.
     * 3. Realiza la búsqueda de la entidad guardada por su nombre utilizando el método `findByNombre`.
     * 4. Verifica que el nombre de la entidad obtenida sea el mismo que el nombre almacenado,
     *    asegurando la consistencia de los datos.
     *
     * Este método ayuda a validar que las operaciones de consulta basadas en el nombre de la entidad
     * funcionan correctamente y que los datos recuperados están íntegros.
     *
     * Anotaciones:
     * - {@code @Test}: Indica que este método es un método de prueba de JUnit. Los métodos anotados
     *   con esta etiqueta serán ejecutados como pruebas.
     * - {@code @Order(4)}: Especifica el orden en el que este método de prueba debe ejecutarse en
     *   relación con otros métodos de prueba en la misma clase. En este caso, el método se ejecutará
     *   cuarto (4).
     */
    @Test
    @Order(4)
    void encontrarEntidadPadreByNombre() {
        log.debug("Iniciando la prueba encontrarEntidadPadreByNombre...");
        var entidadPadre = new EntidadPadre();
        entidadPadre.setNombre("JetBrains");
        entidadPadreRepository.save(entidadPadre);
        log.debug("Buscando EntidadPadre con nombre {}", entidadPadre.getNombre());
        EntidadPadre entidadPadre1 = entidadPadreRepository.findByNombre("JetBrains").orElseThrow();
        log.debug("EntidadPadre encontrada con nombre {}", entidadPadre1.getNombre());
        assertThat(entidadPadre1.getNombre()).isEqualTo("JetBrains");
    }


    /**
     * Prueba unitaria para verificar la creación de una entidad hija y su correcta
     * asociación con una entidad padre, utilizando un repositorio de JPA.
     *
     * <p>Esta prueba realiza los siguientes pasos:
     * <ul>
     *   <li>Crea una instancia de la clase {@code EntidadPadre} y le asigna un nombre.</li>
     *   <li>Crea una instancia de la clase {@code EntidadHija} y la asocia a la entidad padre.</li>
     *   <li>Guarda la entidad padre mediante el repositorio correspondiente, lo cual también
     *   debería guardar la entidad hija debido a la relación entre ambas entidades.</li>
     *   <li>Verifica que la entidad padre fue guardada correctamente y que contiene la entidad hija asociada.</li>
     *   <li>Recupera la entidad padre desde la base de datos y valida que efectivamente tiene la
     *   entidad hija asociada, confirmando la integridad de la relación persistida.</li>
     * </ul>
     *
     * <p>Esta prueba asegura que las relaciones de asociación entre entidades se conservan y
     * funcionan correctamente al persistirlas en la base de datos.
     *
     *
     * <p><strong>Notas adicionales:</strong>
     * El repositorio {@code entidadPadreRepository} es presumiblemente un {@code JpaRepository}
     * o similar de Spring Data JPA, que permite realizar operaciones de CRUD sobre entidades
     * persistentes de la base de datos.
     *
     * <p>Metodología empleada:
     * <ul>
     *   <li>{@code entidadPadreRepository.save(entidadPadre)}: Guarda la entidad padre en la base
     *   de datos, incluyendo la entidad hija asociada gracias a la configuración de la relación
     *   en las clases de entidad.</li>
     *   <li>{@code entidadPadreRepository.findById(id)}: Recupera la entidad padre con un ID
     *   específico desde la base de datos, verificando su estado tras la persistencia.</li>
     *   <li>{@code assertThat}: Realiza las validaciones necesarias para confirmar que las
     *   entidades y relaciones cumplen con las expectativas en términos de datos y comportamiento.</li>
     * </ul>
     */
    @Test
    @Order(5)
    void crearEntidadHija() {
        log.info("Iniciando la prueba crearEntidadHija...");

        // Crear una EntidadPadre
        var entidadPadre = new EntidadPadre();
        entidadPadre.setNombre("Padre de ejemplo");
        log.debug("Created EntidadPadre with name {}", entidadPadre.getNombre());

        // Crear una instancia de EntidadHija
        var entidadHija = new EntidadHija();
        entidadHija.setNombre("Hija de ejemplo");
        log.debug("Creada EntidadHija con nombre {}", entidadHija.getNombre());
        entidadHija.setEntidadPadre(entidadPadre);

        // Asociar la hija al padre
        entidadPadre.setEntidadesHijas(List.of(entidadHija));

        // Guardar la EntidadPadre (esto también debería guardar la EntidadHija debido a la relación)
        var entidadPadreGuardada = entidadPadreRepository.save(entidadPadre);
        log.debug("EntidadPadre and associated EntidadHija saved with IDs {} and {}",
                entidadPadreGuardada.getId(), entidadPadreGuardada.getEntidadesHijas().get(0).getId());

        // Verificar que la entidad padre fue guardada
        assertThat(entidadPadreGuardada.getId()).isPositive();
        assertThat(entidadPadreGuardada.getEntidadesHijas()).isNotNull();
        assertThat(entidadPadreGuardada.getEntidadesHijas().get(0).getNombre()).isEqualTo("Hija de ejemplo");

        // Recuperar el padre por su ID y verificar que tiene la hija asociada
        var entidadPadreRecuperada = entidadPadreRepository.findById(entidadPadreGuardada.getId()).orElseThrow();
        log.debug("Retrieved EntidadPadre with ID {} and associated EntidadHija with ID {}",
                entidadPadreRecuperada.getId(), entidadPadreRecuperada.getEntidadesHijas().get(0).getId());
        assertThat(entidadPadreRecuperada.getEntidadesHijas()).hasSize(1);
        assertThat(entidadPadreRecuperada.getEntidadesHijas().get(0).getNombre()).isEqualTo("Hija de ejemplo");
    }

}