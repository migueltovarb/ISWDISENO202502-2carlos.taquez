package com.inventario.service.impl;

import com.inventario.dto.AlertaDtoResponse;
import com.inventario.exception.EntityNotFoundException;
import com.inventario.model.Alerta;
import com.inventario.model.Producto;
import com.inventario.repository.AlertaRepository;
import com.inventario.service.AlertaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de Alerta.
 */
@Service
@Transactional
public class AlertaServiceImpl implements AlertaService {
    
    private final AlertaRepository alertaRepository;
    
    public AlertaServiceImpl(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }
    
    @Override
    public void generarAlertasParaProducto(Producto producto) {
        List<Alerta> alertasNuevas = new ArrayList<>();
        LocalDateTime ahora = LocalDateTime.now();
        
        // Eliminar alertas no atendidas previas para este producto
        List<Alerta> alertasExistentes = alertaRepository.findByProductoId(producto.getId())
                .stream()
                .filter(a -> !a.getAtendida())
                .collect(Collectors.toList());
        
        // Verificar stock cero (crítica)
        if (producto.getStockActual() == 0) {
            Alerta alertaSinStock = new Alerta();
            alertaSinStock.setProductoId(producto.getId());
            alertaSinStock.setTipo("SIN_STOCK");
            alertaSinStock.setMensaje("El producto " + producto.getNombre() + " (código: " + producto.getCodigo() + 
                    ") se ha quedado sin stock");
            alertaSinStock.setFechaHora(ahora);
            alertaSinStock.setCritica(true);
            alertaSinStock.setAtendida(false);
            alertasNuevas.add(alertaSinStock);
        }
        // Verificar stock bajo (crítica si está muy bajo, no crítica si está cerca del mínimo)
        else if (producto.getStockActual() <= producto.getStockMinimo()) {
            Alerta alertaBajoStock = new Alerta();
            alertaBajoStock.setProductoId(producto.getId());
            alertaBajoStock.setTipo("BAJO_STOCK");
            alertaBajoStock.setMensaje("El producto " + producto.getNombre() + " (código: " + producto.getCodigo() + 
                    ") tiene stock bajo. Actual: " + producto.getStockActual() + ", Mínimo: " + producto.getStockMinimo());
            alertaBajoStock.setFechaHora(ahora);
            // Crítica si está por debajo del 50% del mínimo
            alertaBajoStock.setCritica(producto.getStockActual() < (producto.getStockMinimo() / 2));
            alertaBajoStock.setAtendida(false);
            alertasNuevas.add(alertaBajoStock);
        }
        
        // Si hay nuevas alertas, guardarlas
        if (!alertasNuevas.isEmpty()) {
            // Eliminar alertas anteriores no atendidas del mismo tipo
            alertasExistentes.forEach(alertaRepository::delete);
            alertasNuevas.forEach(alertaRepository::save);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<AlertaDtoResponse> listarAlertasActivas() {
        return alertaRepository.findByAtendidaFalse().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<AlertaDtoResponse> listarAlertasCriticas() {
        return alertaRepository.findByCriticaTrue().stream()
                .filter(a -> !a.getAtendida())
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    @Override
    public void marcarComoAtendida(String id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta no encontrada con ID: " + id));
        
        alerta.setAtendida(true);
        alertaRepository.save(alerta);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<AlertaDtoResponse> listarTodas() {
        return alertaRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    private AlertaDtoResponse convertirADto(Alerta alerta) {
        AlertaDtoResponse dto = new AlertaDtoResponse();
        dto.setId(alerta.getId());
        dto.setProductoId(alerta.getProductoId());
        dto.setTipo(alerta.getTipo());
        dto.setMensaje(alerta.getMensaje());
        dto.setFechaHora(alerta.getFechaHora());
        dto.setCritica(alerta.getCritica());
        dto.setAtendida(alerta.getAtendida());
        return dto;
    }
}

