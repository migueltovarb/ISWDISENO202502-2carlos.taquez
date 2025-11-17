package com.inventario.repository;

import com.inventario.model.Movimiento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio para la entidad Movimiento.
 */
@Repository
public interface MovimientoRepository extends MongoRepository<Movimiento, String> {
    
    /**
     * Busca movimientos por producto.
     */
    List<Movimiento> findByProductoId(String productoId);
    
    /**
     * Busca movimientos en un rango de fechas.
     */
    List<Movimiento> findByFechaMovimientoBetween(LocalDateTime inicio, LocalDateTime fin);
    
    /**
     * Busca movimientos por tipo (ENTRADA o SALIDA).
     */
    List<Movimiento> findByTipo(String tipo);
}

