package com.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO de respuesta para Proveedor.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDtoResponse {
    
    private String id;
    
    private String nombre;
    
    private String telefono;
    
    private String correo;
    
    private String direccion;
    
    private List<String> productosIds;
    
    private Boolean activo;
}

