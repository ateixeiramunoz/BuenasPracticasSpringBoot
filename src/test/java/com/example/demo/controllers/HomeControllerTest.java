package com.example.demo.controllers;

import com.example.demo.MyBaseIntegrationTest;
import com.example.demo.repositories.EntidadPadreRepository;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@Slf4j // Lombok annotation to enable logging
@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest extends MyBaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(
            "mysql:latest"
    ).withDatabaseName("testDB").withUsername("appuser").withPassword("password123");


    @Test
    void shouldAddEntitiesToModel() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/entities"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("entidades"))
                .andExpect(view().name("entidadesHijas"));
    }
}