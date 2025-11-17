package com.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de respuesta para Usuario (sin password).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDtoResponse {
    
    private String id;
    
    private String username;
    
    private String rol;
    
    private Boolean activo;
}

