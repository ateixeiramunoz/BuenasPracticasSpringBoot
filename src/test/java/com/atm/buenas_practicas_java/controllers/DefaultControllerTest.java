package com.atm.buenas_practicas_java.controllers;

import com.atm.buenas_practicas_java.MyBaseIntegrationTest;
import com.atm.buenas_practicas_java.entities.EntidadHija;
import com.atm.buenas_practicas_java.entities.EntidadPadre;
import com.atm.buenas_practicas_java.repositories.EntidadHijaRepository;
import com.atm.buenas_practicas_java.repositories.EntidadPadreRepository;
import lombok.extern.log4j.Log4j2;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@Log4j2
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@AutoConfigureMockMvc
class DefaultControllerTest extends MyBaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EntidadPadreRepository entidadPadreService;

    @Autowired
    EntidadHijaRepository entidadHijaService;


    @Autowired
    EntidadPadreRepository entidadPadreRepository;

    @Autowired
    EntidadHijaRepository entidadHijaRepository;


    @Test
    void shouldReturnProtectedView() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/protected"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldAddProtectedEntitiesToModel() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/protected")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("user:password".getBytes())))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("entidades"))
                .andExpect(view().name("entidadesPadre"));
    }

    @Test
    void shouldAddEntitiesToModel() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/entities"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("entidades"))
                .andExpect(view().name("entidadesHijas"));
    }

    @Test
    void shouldDeleteEntidadHijaAndRedirect() throws Exception {

        int numeroEntidades = 1;
        EntidadPadre entidadPadre = entidadPadreService.save(new EntidadPadre("Entidad-1"));
        EntidadHija entidadHija = new EntidadHija("Hija de " + entidadPadre.getNombre());
        entidadHija.setEntidadPadre(entidadPadre);
        entidadHijaService.save(entidadHija);

        // Arrange
        Long testId = entidadHija.getId();

        // Act & Assert
        mockMvc.perform(post("/entidades/deleteHija/{id}", testId)
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("user:password".getBytes()))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/entities"));

        assert entidadHijaService.findAll().isEmpty();


    }

}