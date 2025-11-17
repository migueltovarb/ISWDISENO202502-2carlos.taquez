package com.inventario.repository;

import com.inventario.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Usuario.
 */
@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    
    /**
     * Busca un usuario por su username (debe ser único).
     */
    Optional<Usuario> findByUsername(String username);
    
    /**
     * Busca usuarios activos.
     */
    java.util.List<Usuario> findByActivoTrue();
}

