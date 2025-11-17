package com.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO de respuesta para Producto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDtoResponse {
    
    private String id;
    
    private String codigo;
    
    private String nombre;
    
    private String descripcion;
    
    private Integer cantidadInicial;
    
    private Integer stockActual;
    
    private Integer stockMinimo;
    
    private Double precioUnitario;
    
    private Boolean activo;
    
    private LocalDateTime fechaCreacion;
    
    private LocalDateTime fechaActualizacion;
}

