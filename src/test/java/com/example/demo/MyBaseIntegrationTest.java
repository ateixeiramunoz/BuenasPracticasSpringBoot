package com.example.demo;

//https://stackoverflow.com/a/77113817/3617531

import com.example.demo.repositories.EntidadHijaRepository;
import com.example.demo.repositories.EntidadPadreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
public abstract class MyBaseIntegrationTest {

    @ServiceConnection
    protected static final MySQLContainer<?> dbContainer = new MySQLContainer<>().withDatabaseName("testDB").withUsername("appuser").withPassword("password123");

    @Autowired
    EntidadPadreRepository entidadPadreRepository;

    @Autowired
    EntidadHijaRepository entidadHijaRepository;

    static {
        dbContainer.start();
    }

}

