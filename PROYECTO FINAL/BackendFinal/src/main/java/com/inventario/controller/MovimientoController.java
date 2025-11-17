package com.inventario.controller;

import com.inventario.dto.MovimientoDto;
import com.inventario.dto.MovimientoStockDto;
import com.inventario.service.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador REST para la gestión de movimientos de inventario.
 * 
 * Endpoints:
 * - GET /api/movimientos                  → Listar todos (RF_019)
 * - GET /api/movimientos/producto/{id}    → Listar por producto
 * - GET /api/movimientos/rango            → Filtrar por rango de fechas (RF_020)
 */
@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin(origins = "*")
public class MovimientoController {
    
    private final MovimientoService movimientoService;
    
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }
    
    /**
     * Registra una entrada de stock para un producto.
     * 
     * Ejemplo JSON:
     * {
     *   "cantidad": 50,
     *   "motivo": "Compra de proveedor"
     * }
     */
    @PostMapping("/producto/{productoId}/entrada")
    public ResponseEntity<MovimientoDto> registrarEntrada(
            @PathVariable String productoId,
            @Valid @RequestBody MovimientoStockDto movimientoDto) {
        MovimientoDto movimiento = movimientoService.registrarEntrada(
                productoId, 
                movimientoDto.getCantidad(), 
                movimientoDto.getMotivo());
        return ResponseEntity.status(HttpStatus.CREATED).body(movimiento);
    }
    
    /**
     * Registra una salida de stock para un producto.
     * 
     * Ejemplo JSON:
     * {
     *   "cantidad": 10,
     *   "motivo": "Venta"
     * }
     */
    @PostMapping("/producto/{productoId}/salida")
    public ResponseEntity<MovimientoDto> registrarSalida(
            @PathVariable String productoId,
            @Valid @RequestBody MovimientoStockDto movimientoDto) {
        MovimientoDto movimiento = movimientoService.registrarSalida(
                productoId, 
                movimientoDto.getCantidad(), 
                movimientoDto.getMotivo());
        return ResponseEntity.status(HttpStatus.CREATED).body(movimiento);
    }
    
    /**
     * Lista todos los movimientos (RF_019).
     */
    @GetMapping
    public ResponseEntity<List<MovimientoDto>> listarTodos() {
        List<MovimientoDto> movimientos = movimientoService.listarTodos();
        return ResponseEntity.ok(movimientos);
    }
    
    /**
     * Lista movimientos por producto.
     */
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<MovimientoDto>> listarPorProducto(@PathVariable String productoId) {
        List<MovimientoDto> movimientos = movimientoService.listarPorProducto(productoId);
        return ResponseEntity.ok(movimientos);
    }
    
    /**
     * Lista movimientos por rango de fechas (RF_020).
     * Query parameters: ?inicio=2024-01-01T00:00:00&fin=2024-12-31T23:59:59
     */
    @GetMapping("/rango")
    public ResponseEntity<List<MovimientoDto>> listarPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        List<MovimientoDto> movimientos = movimientoService.listarPorRangoFechas(inicio, fin);
        return ResponseEntity.ok(movimientos);
    }
}

