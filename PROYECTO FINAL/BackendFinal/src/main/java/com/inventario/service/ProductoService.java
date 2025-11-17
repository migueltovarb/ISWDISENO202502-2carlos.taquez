package com.inventario.service;

import com.inventario.dto.ProductoDtoCreate;
import com.inventario.dto.ProductoDtoResponse;
import com.inventario.dto.ProductoDtoUpdate;

import java.util.List;

/**
 * Interfaz del servicio de Producto.
 */
public interface ProductoService {
    
    /**
     * Crea un nuevo producto (RF_001).
     */
    ProductoDtoResponse crear(ProductoDtoCreate productoDtoCreate);
    
    /**
     * Actualiza un producto existente (RF_002).
     */
    ProductoDtoResponse actualizar(String id, ProductoDtoUpdate productoDtoUpdate);
    
    /**
     * Elimina lógicamente un producto (RF_003).
     */
    void eliminarLogico(String id);
    
    /**
     * Obtiene un producto por su ID (RF_005).
     */
    ProductoDtoResponse obtenerPorId(String id);
    
    /**
     * Busca productos por código o nombre (RF_004).
     */
    List<ProductoDtoResponse> buscarPorCodigoOCriterio(String texto);
    
    /**
     * Lista todos los productos (RF_004).
     */
    List<ProductoDtoResponse> listar();
    
    /**
     * Lista productos con stock bajo (RF_013, RF_014).
     */
    List<ProductoDtoResponse> productosConStockBajo();
    
    /**
     * Actualiza el stock de un producto basado en un movimiento (interno).
     */
    void actualizarStockPorMovimiento(String productoId, String tipo, Integer cantidad);
    
    /**
     * Verifica y genera alertas para un producto.
     */
    void verificarAlertas(String productoId);
}

