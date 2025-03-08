package com.example.demo.controllers;

import com.example.demo.repositories.EntidadPadreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final EntidadPadreRepository repository;

    public HomeController(EntidadPadreRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("true")
    @GetMapping("/entities")
    public String listEntities(Model model)
    {
        model.addAttribute("entities", repository.findAll());
        // Logic for fetching and displaying the list of entities
        return "entitiesList"; // View name
    }

}
