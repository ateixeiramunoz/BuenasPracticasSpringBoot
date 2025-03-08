package com.example.demo.controllers;

import com.example.demo.repositories.EntidadPadreRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador encargado de manejar las solicitudes relacionadas con la entidad principal.
 *
 * Este controlador utiliza la anotación {@code @Controller} para ser detectado como un componente
 * Spring MVC y maneja solicitudes HTTP. Su objetivo principal es gestionar las operaciones
 * necesarias para mostrar una lista de entidades en la vista correspondiente.
 *
 * Anotaciones importantes:
 * - {@code @Controller}: Indica que esta clase se comporta como un controlador Spring MVC.
 * - {@code @PreAuthorize}: Define que el acceso a ciertos métodos esté restringido
 *   según las reglas de autorización establecidas.
 *
 * Dependencias:
 * - {@code EntidadPadreRepository}: Interfaz del repositorio que permite interactuar con
 *   la base de datos para operaciones de persistencia y consulta relacionadas con
 *   la entidad padre.
 *
 * Métodos principales:
 * - {@code listEntities}: Maneja solicitudes GET a la URL "/entities", recupera los
 *   datos de las entidades desde la base de datos y los pasa al modelo para mostrarlos
 *   en una vista.
 *
 * @Author HomeController.java
 */
@Controller
public class HomeController {

    private final EntidadPadreRepository repository;

    /**
     * Controlador diseñado para gestionar las operaciones relacionadas con la entidad EntidadPadre.
     *
     * @param repository El repositorio de tipo EntidadPadreRepository que gestiona las operaciones de acceso a los datos
     *                   de la entidad persistente EntidadPadre.
     * @Author Desarrollador desconocido (por favor, añadir si existe información disponible).
     */
    public HomeController(EntidadPadreRepository repository) {
        this.repository = repository;
    }

    /**
     * Método para listar todas las entidades y agregarlas al modelo para su visualización.
     *
     * Este método está protegido por la anotación {@code @PreAuthorize}, lo que significa
     * que su ejecución puede estar condicionada a ciertas reglas de seguridad.
     * Además, utiliza un {@code @GetMapping} para asociarlo con la ruta HTTP GET "/entities".
     *
     * @param model El objeto {@link Model} utilizado para pasar atributos desde el controlador
     *              a la vista. En este caso, se agrega una lista de entidades al modelo.
     * @return El nombre de la vista, representado como un {@code String}. Esta cadena corresponde
     *         al archivo HTML o vista que representará la lista de entidades.
     * @Author No especificado
     */
    @PreAuthorize("true")
    @GetMapping("/entities")
    public String listEntities(Model model)
    {
        model.addAttribute("entities", repository.findAll());

        return "entitiesList"; // View name
    }

}
