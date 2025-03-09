package com.eoi.buenas_practicas_java.controllers;

import com.eoi.buenas_practicas_java.MyBaseIntegrationTest;
import lombok.extern.slf4j.Slf4j;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import com.eoi.buenas_practicas_java.services.EntidadHijaService;


import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@Slf4j // Lombok annotation to enable logging
@SpringBootTest
@AutoConfigureMockMvc
class DefaultControllerTest extends MyBaseIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntidadHijaService entidadHijaService;

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
        // Arrange
        Long testId = 1L;

        // Act & Assert
        mockMvc.perform(post("/entidades/delete/{id}", testId).header("Authorization", "Basic " + Base64.getEncoder().encodeToString("user:password".getBytes())))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/entities"));

        // Verify
        verify(entidadHijaService).deleteById(testId);
    }
}