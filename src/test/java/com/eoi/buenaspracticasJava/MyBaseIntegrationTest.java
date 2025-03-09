package com.eoi.buenaspracticasJava;

//https://stackoverflow.com/a/77113817/3617531

import com.eoi.buenaspracticasJava.repositories.EntidadHijaRepository;
import com.eoi.buenaspracticasJava.repositories.EntidadPadreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
public abstract class MyBaseIntegrationTest {

    @ServiceConnection
    protected static final MySQLContainer<?> dbContainer = new MySQLContainer<>("mysql:latest").withDatabaseName("testDB").withUsername("appuser").withPassword("password123");

    @Autowired
    EntidadPadreRepository entidadPadreRepository;

    @Autowired
    EntidadHijaRepository entidadHijaRepository;


    static {
        dbContainer.start();
    }

}

