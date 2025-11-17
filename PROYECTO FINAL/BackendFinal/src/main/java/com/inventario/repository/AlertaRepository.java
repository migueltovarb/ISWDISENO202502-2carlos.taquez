package com.inventario.repository;

import com.inventario.model.Alerta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Alerta.
 */
@Repository
public interface AlertaRepository extends MongoRepository<Alerta, String> {
    
    /**
     * Busca alertas por producto.
     */
    List<Alerta> findByProductoId(String productoId);
    
    /**
     * Busca alertas críticas.
     */
    List<Alerta> findByCriticaTrue();
    
    /**
     * Busca alertas no atendidas.
     */
    List<Alerta> findByAtendidaFalse();
    
    /**
     * Busca alertas activas (no atendidas) y críticas.
     */
    List<Alerta> findByAtendidaFalseAndCriticaTrue();
}

