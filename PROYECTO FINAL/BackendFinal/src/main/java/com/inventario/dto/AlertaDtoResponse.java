package com.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO de respuesta para Alerta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertaDtoResponse {
    
    private String id;
    
    private String productoId;
    
    private String tipo;
    
    private String mensaje;
    
    private LocalDateTime fechaHora;
    
    private Boolean critica;
    
    private Boolean atendida;
}

