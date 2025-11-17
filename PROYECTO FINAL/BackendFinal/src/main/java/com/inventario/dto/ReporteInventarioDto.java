package com.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para el reporte general de inventario.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteInventarioDto {
    
    private Integer totalProductos;
    
    private Integer productosActivos;
    
    private Integer productosConStockBajo;
    
    private Integer totalAlertasActivas;
    
    private Integer alertasCriticas;
    
    private List<ProductoDtoResponse> productos;
    
    private List<AlertaDtoResponse> alertas;
}

