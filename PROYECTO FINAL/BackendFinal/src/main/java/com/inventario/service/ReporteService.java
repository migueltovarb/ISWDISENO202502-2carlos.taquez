package com.inventario.service;

import com.inventario.dto.MovimientoDto;
import com.inventario.dto.ReporteInventarioDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz del servicio de Reportes.
 */
public interface ReporteService {
    
    /**
     * Genera un reporte general del inventario (RF_018).
     */
    ReporteInventarioDto reporteGeneralInventario();
    
    /**
     * Genera un reporte de movimientos en un rango de fechas (RF_019, RF_020).
     */
    List<MovimientoDto> reporteMovimientos(LocalDateTime inicio, LocalDateTime fin);
}

