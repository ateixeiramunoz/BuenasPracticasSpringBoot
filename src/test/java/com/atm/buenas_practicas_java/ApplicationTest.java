package com.atm.buenas_practicas_java;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;


import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ApplicationTest extends MyBaseIntegrationTest{
    /**
     * Ensures that the Spring application context loads successfully when the main method is called.
     */
    @Test
    @Order(1)
    void contextLoads() {
        log.info("Iniciando la prueba de contextos...");
        assertThat(dbContainer.isRunning()).isTrue();
        assertThat(this.entidadHijaRepository.findAll()).hasSize(0);
        assertThat(this.entidadPadreRepository.findAll()).hasSize(0);
        for (String s : Arrays.asList("Context loaded", "Database name: " + dbContainer.getDatabaseName(), "Username: " + dbContainer.getUsername(), "Password: " + dbContainer.getPassword())) {
            log.info(s);
        }
        log.info("Contexto de Spring cargado con éxito.");
    }


}
