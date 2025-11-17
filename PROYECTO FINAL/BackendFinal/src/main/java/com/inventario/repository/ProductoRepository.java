package com.inventario.repository;

import com.inventario.model.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad Producto.
 */
@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {
    
    /**
     * Busca un producto por su código (debe ser único).
     */
    Optional<Producto> findByCodigo(String codigo);
    
    /**
     * Busca productos cuyo nombre contenga el texto dado (case-insensitive).
     */
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    
    /**
     * Busca productos con stock actual menor o igual al valor dado.
     */
    List<Producto> findByStockActualLessThanEqual(Integer stock);
    
    /**
     * Busca productos activos.
     */
    List<Producto> findByActivoTrue();
}

