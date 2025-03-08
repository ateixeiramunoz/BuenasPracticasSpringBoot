package com.example.demo.entities;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
@DataJpaTest
@Testcontainers
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
