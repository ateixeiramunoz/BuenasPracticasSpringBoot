package com.atm.buenas_practicas_java;

//https://stackoverflow.com/a/77113817/3617531


import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;


public abstract class PostgreSQLContainerTest {

    @ServiceConnection
    protected static final PostgreSQLContainer<?> dbContainer = new PostgreSQLContainer<>("postgres:latest").withDatabaseName("testDB").withUsername("appuser").withPassword("password123");

    static {
        dbContainer.start();
        // Add a shutdown hook to ensure the container stops after all tests have completed
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (dbContainer.isRunning()) {
                dbContainer.stop();
            }
        }));
    }

}

