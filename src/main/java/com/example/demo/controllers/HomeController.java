package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/entities")
    public String listEntities(Model model) {
        // Logic for fetching and displaying the list of entities
        return "entitiesList"; // View name
    }


}
