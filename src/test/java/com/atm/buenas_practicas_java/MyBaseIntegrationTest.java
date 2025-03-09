package com.atm.buenas_practicas_java;

//https://stackoverflow.com/a/77113817/3617531

import com.atm.buenas_practicas_java.repositories.EntidadHijaRepository;
import com.atm.buenas_practicas_java.repositories.EntidadPadreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
public abstract class MyBaseIntegrationTest {

    @ServiceConnection
    protected static final PostgreSQLContainer<?> dbContainer = new PostgreSQLContainer<>("postgres:latest").withDatabaseName("testDB").withUsername("appuser").withPassword("password123");

    @Autowired
    EntidadPadreRepository entidadPadreRepository;

    @Autowired
    EntidadHijaRepository entidadHijaRepository;


    static {
        dbContainer.start();
    }

}

