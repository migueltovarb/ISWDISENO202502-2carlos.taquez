package com.inventario.service;

import com.inventario.dto.AlertaDtoResponse;
import com.inventario.model.Producto;

import java.util.List;

/**
 * Interfaz del servicio de Alerta.
 */
public interface AlertaService {
    
    /**
     * Genera alertas para un producto basado en su stock (RF_015, RF_016, RF_017).
     */
    void generarAlertasParaProducto(Producto producto);
    
    /**
     * Lista todas las alertas activas (no atendidas).
     */
    List<AlertaDtoResponse> listarAlertasActivas();
    
    /**
     * Lista solo las alertas críticas (RF_016, RF_017).
     */
    List<AlertaDtoResponse> listarAlertasCriticas();
    
    /**
     * Marca una alerta como atendida.
     */
    void marcarComoAtendida(String id);
    
    /**
     * Lista todas las alertas.
     */
    List<AlertaDtoResponse> listarTodas();
}

