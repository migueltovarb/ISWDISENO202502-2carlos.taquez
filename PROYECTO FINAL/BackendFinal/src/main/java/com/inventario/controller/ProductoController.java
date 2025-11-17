package com.inventario.controller;

import com.inventario.dto.MovimientoStockDto;
import com.inventario.dto.ProductoDtoCreate;
import com.inventario.dto.ProductoDtoResponse;
import com.inventario.dto.ProductoDtoUpdate;
import com.inventario.service.MovimientoService;
import com.inventario.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de productos.
 * 
 * Endpoints:
 * - POST /api/productos           → Crear producto (RF_001)
 * - PUT /api/productos/{id}       → Editar producto (RF_002)
 * - DELETE /api/productos/{id}    → Eliminar lógico (RF_003)
 * - GET /api/productos            → Listar todos (RF_004, RF_005)
 * - GET /api/productos/buscar     → Buscar por nombre o código (RF_004)
 * - GET /api/productos/{id}       → Detalle del producto (RF_005)
 * - PATCH /api/productos/{id}/stock/entrada → Registrar entrada (RF_006, RF_007)
 * - PATCH /api/productos/{id}/stock/salida  → Registrar salida (RF_006, RF_007)
 */
@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    
    private final ProductoService productoService;
    private final MovimientoService movimientoService;
    
    public ProductoController(ProductoService productoService, MovimientoService movimientoService) {
        this.productoService = productoService;
        this.movimientoService = movimientoService;
    }
    
    /**
     * Crea un nuevo producto (RF_001).
     * 
     * Ejemplo JSON:
     * {
     *   "codigo": "PROD-001",
     *   "nombre": "Laptop Dell",
     *   "descripcion": "Laptop Dell Inspiron 15",
     *   "cantidadInicial": 100,
     *   "stockMinimo": 10,
     *   "precioUnitario": 15000.00
     * }
     */
    @PostMapping
    public ResponseEntity<ProductoDtoResponse> crear(@Valid @RequestBody ProductoDtoCreate productoDtoCreate) {
        ProductoDtoResponse producto = productoService.crear(productoDtoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }
    
    /**
     * Actualiza un producto existente (RF_002).
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDtoResponse> actualizar(
            @PathVariable String id,
            @Valid @RequestBody ProductoDtoUpdate productoDtoUpdate) {
        ProductoDtoResponse producto = productoService.actualizar(id, productoDtoUpdate);
        return ResponseEntity.ok(producto);
    }
    
    /**
     * Elimina lógicamente un producto (RF_003).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        productoService.eliminarLogico(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Obtiene un producto por su ID (RF_005).
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDtoResponse> obtenerPorId(@PathVariable String id) {
        ProductoDtoResponse producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }
    
    /**
     * Lista todos los productos activos (RF_004, RF_005).
     */
    @GetMapping
    public ResponseEntity<List<ProductoDtoResponse>> listar() {
        List<ProductoDtoResponse> productos = productoService.listar();
        return ResponseEntity.ok(productos);
    }
    
    /**
     * Busca productos por nombre o código (RF_004).
     * Query parameter: ?texto=nombre_o_codigo
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoDtoResponse>> buscar(
            @RequestParam String texto) {
        List<ProductoDtoResponse> productos = productoService.buscarPorCodigoOCriterio(texto);
        return ResponseEntity.ok(productos);
    }
    
    /**
     * Registra una entrada de stock (RF_006, RF_007).
     * 
     * Ejemplo JSON:
     * {
     *   "cantidad": 50,
     *   "motivo": "Compra de proveedor"
     * }
     */
    @PatchMapping("/{id}/stock/entrada")
    public ResponseEntity<ProductoDtoResponse> registrarEntrada(
            @PathVariable String id,
            @Valid @RequestBody MovimientoStockDto movimientoDto) {
        // Registra el movimiento y actualiza el stock
        movimientoService.registrarEntrada(id, movimientoDto.getCantidad(), movimientoDto.getMotivo());
        // Retorna el producto actualizado
        ProductoDtoResponse producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }
    
    /**
     * Registra una salida de stock (RF_006, RF_007).
     * 
     * Ejemplo JSON:
     * {
     *   "cantidad": 10,
     *   "motivo": "Venta"
     * }
     */
    @PatchMapping("/{id}/stock/salida")
    public ResponseEntity<ProductoDtoResponse> registrarSalida(
            @PathVariable String id,
            @Valid @RequestBody MovimientoStockDto movimientoDto) {
        // Registra el movimiento y actualiza el stock
        movimientoService.registrarSalida(id, movimientoDto.getCantidad(), movimientoDto.getMotivo());
        // Retorna el producto actualizado
        ProductoDtoResponse producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }
    
    /**
     * Lista productos con stock bajo (RF_013, RF_014).
     */
    @GetMapping("/stock-bajo")
    public ResponseEntity<List<ProductoDtoResponse>> productosConStockBajo() {
        List<ProductoDtoResponse> productos = productoService.productosConStockBajo();
        return ResponseEntity.ok(productos);
    }
}

