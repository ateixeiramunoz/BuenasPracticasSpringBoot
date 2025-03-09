package com.eoi.buenaspracticasJava.entities;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
@SpringBootTest
class EntidadPadreTest {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(
            "mysql:latest"
    ).withDatabaseName("testDB").withUsername("appuser").withPassword("password123");

    @Test
    void testEntidadPadreConstructorWithNombre() {
        // Arrange
        String expectedNombre = "Test Nombre";

        // Act
        EntidadPadre entidadPadre = new EntidadPadre(expectedNombre);

        // Assert
        assertEquals(expectedNombre, entidadPadre.getNombre(), "El nombre no fue inicializado correctamente en el constructor.");
    }


}
