package com.inventario.service;

import com.inventario.dto.ProveedorDtoCreate;
import com.inventario.dto.ProveedorDtoResponse;
import com.inventario.dto.ProveedorDtoUpdate;

import java.util.List;

/**
 * Interfaz del servicio de Proveedor.
 */
public interface ProveedorService {
    
    /**
     * Crea un nuevo proveedor (RF_008).
     */
    ProveedorDtoResponse crear(ProveedorDtoCreate proveedorDtoCreate);
    
    /**
     * Actualiza un proveedor existente (RF_009).
     */
    ProveedorDtoResponse actualizar(String id, ProveedorDtoUpdate proveedorDtoUpdate);
    
    /**
     * Elimina lógicamente un proveedor (RF_010).
     */
    void eliminarLogico(String id);
    
    /**
     * Obtiene un proveedor por su ID.
     */
    ProveedorDtoResponse obtenerPorId(String id);
    
    /**
     * Lista todos los proveedores (RF_011).
     */
    List<ProveedorDtoResponse> listar();
    
    /**
     * Filtra proveedores por producto (RF_012).
     */
    List<ProveedorDtoResponse> filtrarPorProducto(String productoId);
}

