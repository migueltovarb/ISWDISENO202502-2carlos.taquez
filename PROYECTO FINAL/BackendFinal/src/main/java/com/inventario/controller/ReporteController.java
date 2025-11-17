package com.inventario.controller;

import com.inventario.dto.MovimientoDto;
import com.inventario.dto.ReporteInventarioDto;
import com.inventario.service.ReporteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador REST para la generación de reportes.
 * 
 * Endpoints:
 * - GET /api/reportes/inventario   → Reporte general (RF_018)
 * - GET /api/reportes/movimientos  → Reporte de movimientos (RF_019, RF_020)
 */
@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {
    
    private final ReporteService reporteService;
    
    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }
    
    /**
     * Genera un reporte general del inventario (RF_018).
     * Incluye estadísticas y listas de productos y alertas.
     */
    @GetMapping("/inventario")
    public ResponseEntity<ReporteInventarioDto> reporteGeneralInventario() {
        ReporteInventarioDto reporte = reporteService.reporteGeneralInventario();
        return ResponseEntity.ok(reporte);
    }
    
    /**
     * Genera un reporte de movimientos en un rango de fechas (RF_019, RF_020).
     * Query parameters: ?inicio=2024-01-01T00:00:00&fin=2024-12-31T23:59:59
     */
    @GetMapping("/movimientos")
    public ResponseEntity<List<MovimientoDto>> reporteMovimientos(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        List<MovimientoDto> movimientos = reporteService.reporteMovimientos(inicio, fin);
        return ResponseEntity.ok(movimientos);
    }
}

