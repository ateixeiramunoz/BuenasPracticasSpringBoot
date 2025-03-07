package loaders;

import entities.EntidadHija;
import entities.EntidadHijaRepository;
import entities.EntidadPadre;
import entities.EntidadPadreRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

/**
 * Clase de configuración que permite cargar datos iniciales en los repositorios
 * de entidades para diferentes perfiles de configuración.
 *
 * Esta clase es útil para inicializar datos predefinidos utilizados durante el
 * desarrollo o en entornos locales.
 *
 * Se utiliza la anotación @Configuration para indicar que es una clase de configuración
 * de Spring, y métodos específicos anotados con @Profile para definir qué datos
 * iniciales se cargarán según el perfil activo.
 */
@Configuration
@Log4j2
public class InitialDataLoader {

    private final EntidadPadreRepository repository;
    private final EntidadHijaRepository entidadHijaRepository;

    /**
     * Clase de configuración que permite cargar datos iniciales en los repositorios
     * de entidades para diferentes perfiles de configuración.
     *
     * Esta clase es útil para inicializar datos predefinidos utilizados durante el
     * desarrollo o en entornos específicos según el perfil.
     *
     * **Anotaciones utilizadas**:
     * - `@Configuration`: Define esta clase como una clase de configuración de Spring.
     *   Permite registrar beans en el contexto de la aplicación y gestionar configuraciones específicas.
     *
     * - `@Log4j2`: Habilita el uso de Log4j2 para registrar mensajes de log importantes,
     *   utilizados para monitoreo y depuración de la aplicación.
     *
     * Cada método anotado con `@Profile` y `@PostConstruct` permite la carga de datos
     * iniciales dependiendo del perfil activo.
     */
    public InitialDataLoader(EntidadPadreRepository repository, EntidadHijaRepository entidadHijaRepository) {
        this.repository = repository;
        this.entidadHijaRepository = entidadHijaRepository;
    }


    /**
     * Método encargado de cargar datos iniciales en los repositorios para el
     * perfil de desarrollo. Este método será ejecutado después de la construcción
     * del bean (fase post-construction) y solo será activado cuando el perfil
     * activo de la aplicación sea "desarrollo".
     *
     * **Anotaciones utilizadas:**
     *
     * - `@PostConstruct`: Indica que este método se ejecutará automáticamente
     *   después de que el bean haya sido construido y todas las dependencias hayan
     *   sido inyectadas. Ideal para inicialización de datos.
     *
     * - `@Profile("desarrollo")`: Especifica que este método solo debe ejecutarse
     *   cuando el perfil activo de la aplicación es "desarrollo". Proporciona una
     *   forma de definir configuraciones o comportamientos específicos, dependiendo
     *   del entorno.
     *
     * **Funcionalidad del método:**
     *
     * Este método realiza lo siguiente:
     * 1. Crea un conjunto de instancias de la clase `EntidadPadre` con nombres
     *    predeterminados ("Entidad0" a "Entidad9").
     * 2. Persiste estas entidades en el repositorio `EntidadPadreRepository`.
     * 3. Crea una instancia adicional de `EntidadPadre` con el nombre "EntidadPadre99".
     * 4. Crea una instancia de `EntidadHija`, estableciendo "EntidadHija99" como su
     *    nombre, y vincula esta instancia con la entidad padre recién creada.
     * 5. Persiste tanto la entidad padre adicional como la entidad hija vinculada,
     *    utilizando los repositorios correspondientes.
     *
     * Este método es útil para probar y validar comportamientos de la aplicación
     * en el entorno de desarrollo con datos iniciales predefinidos.
     *
     * **Mensajes de log añadidos:**
     * - Mensajes informativos antes y después de realizar operaciones significativas,
     *   como la creación y persistencia de entidades, para facilitar el monitoreo y
     *   la depuración del sistema.
     */
    @PostConstruct
    @Profile("desarrollo")
    public void loadDataDesarrollo() {
        log.info("Iniciando la carga de datos para el perfil de desarrollo.");
        int numeroEntidades = 10;

        EntidadPadre[] entidades = new EntidadPadre[numeroEntidades];
        Arrays.setAll(entidades, i -> new EntidadPadre());

        entidades[0].setNombre("Entidad0");
        entidades[1].setNombre("Entidad1");
        entidades[2].setNombre("Entidad2");
        entidades[3].setNombre("Entidad3");
        entidades[4].setNombre("Entidad4");
        entidades[5].setNombre("Entidad5");
        entidades[6].setNombre("Entidad6");
        entidades[7].setNombre("Entidad7");
        entidades[8].setNombre("Entidad8");
        entidades[9].setNombre("Entidad9");

        log.info("Persistiendo lote de entidades padre en el repositorio...");
        repository.saveAll(Arrays.asList(entidades));

        EntidadPadre entidadPadre = new EntidadPadre();
        entidadPadre.setNombre("EntidadPadre99");

        EntidadHija entidadHija = new EntidadHija();
        entidadHija.setNombre("EntidadHija99");

        entidadHija.setEntidadPadre(entidadPadre);

        log.info("Persistiendo nueva entidad padre con nombre 'EntidadPadre99' y su entidad hija asociada...");
        repository.save(entidadPadre);
        entidadHijaRepository.save(entidadHija);

    }


    @PostConstruct
    @Profile("produccionDATALOAD")
    public void loadDataProduccion() {
        log.info("Iniciando la carga de datos para el perfil de producción.");
        log.info("Datos de producción aún no definidos. Este método requiere implementación adicional.");
    }





}
