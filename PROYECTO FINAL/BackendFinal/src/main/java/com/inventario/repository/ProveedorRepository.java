package com.inventario.repository;

import com.inventario.model.Proveedor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Proveedor.
 */
@Repository
public interface ProveedorRepository extends MongoRepository<Proveedor, String> {
    
    /**
     * Busca proveedores cuyo nombre contenga el texto dado (case-insensitive).
     */
    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);
    
    /**
     * Busca proveedores que suministran un producto específico.
     */
    List<Proveedor> findByProductosIdsContains(String productoId);
    
    /**
     * Busca proveedores activos.
     */
    List<Proveedor> findByActivoTrue();
}

