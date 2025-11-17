package com.inventario.controller;

import com.inventario.dto.AlertaDtoResponse;
import com.inventario.service.AlertaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de alertas.
 * 
 * Endpoints:
 * - GET /api/alertas                → Listar todas las alertas
 * - GET /api/alertas/activas        → Listar solo activas
 * - GET /api/alertas/criticas       → Listar solo críticas (RF_016, RF_017)
 * - PATCH /api/alertas/{id}/atender → Marcar alerta como atendida
 */
@RestController
@RequestMapping("/api/alertas")
@CrossOrigin(origins = "*")
public class AlertaController {
    
    private final AlertaService alertaService;
    
    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }
    
    /**
     * Lista todas las alertas.
     */
    @GetMapping
    public ResponseEntity<List<AlertaDtoResponse>> listarTodas() {
        List<AlertaDtoResponse> alertas = alertaService.listarTodas();
        return ResponseEntity.ok(alertas);
    }
    
    /**
     * Lista solo las alertas activas (no atendidas) (RF_015).
     */
    @GetMapping("/activas")
    public ResponseEntity<List<AlertaDtoResponse>> listarAlertasActivas() {
        List<AlertaDtoResponse> alertas = alertaService.listarAlertasActivas();
        return ResponseEntity.ok(alertas);
    }
    
    /**
     * Lista solo las alertas críticas (RF_016, RF_017).
     */
    @GetMapping("/criticas")
    public ResponseEntity<List<AlertaDtoResponse>> listarAlertasCriticas() {
        List<AlertaDtoResponse> alertas = alertaService.listarAlertasCriticas();
        return ResponseEntity.ok(alertas);
    }
    
    /**
     * Marca una alerta como atendida.
     */
    @PatchMapping("/{id}/atender")
    public ResponseEntity<Void> marcarComoAtendida(@PathVariable String id) {
        alertaService.marcarComoAtendida(id);
        return ResponseEntity.noContent().build();
    }
}

