package com.atm.buenas_practicas_java.repositories;

import com.atm.buenas_practicas_java.entities.EntidadPadre;
import com.atm.buenas_practicas_java.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * Repository interface for managing {@link EntidadPadre} entities.
 * Extends {@link JpaRepository} to provide standard CRUD operations.
 * Custom query methods may be defined as needed.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String jetBrains);

    Collection<Object> findByNombreContaining(String padre);
}