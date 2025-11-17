package com.inventario.service;

import com.inventario.dto.MovimientoDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz del servicio de Movimiento.
 */
public interface MovimientoService {
    
    /**
     * Registra una entrada de stock (RF_006, RF_007).
     */
    MovimientoDto registrarEntrada(String productoId, Integer cantidad, String motivo);
    
    /**
     * Registra una salida de stock (RF_006, RF_007).
     */
    MovimientoDto registrarSalida(String productoId, Integer cantidad, String motivo);
    
    /**
     * Lista movimientos por producto.
     */
    List<MovimientoDto> listarPorProducto(String productoId);
    
    /**
     * Lista movimientos por rango de fechas (RF_020).
     */
    List<MovimientoDto> listarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin);
    
    /**
     * Lista todos los movimientos (RF_019).
     */
    List<MovimientoDto> listarTodos();
}

