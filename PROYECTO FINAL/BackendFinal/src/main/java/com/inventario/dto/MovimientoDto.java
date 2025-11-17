package com.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO de respuesta para Movimiento.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDto {
    
    private String id;
    
    private String productoId;
    
    private String tipo;
    
    private Integer cantidad;
    
    private String motivo;
    
    private LocalDateTime fechaMovimiento;
}

