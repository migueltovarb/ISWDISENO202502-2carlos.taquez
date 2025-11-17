package com.inventario.controller;

import com.inventario.dto.ProveedorDtoCreate;
import com.inventario.dto.ProveedorDtoResponse;
import com.inventario.dto.ProveedorDtoUpdate;
import com.inventario.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de proveedores.
 * 
 * Endpoints:
 * - POST /api/proveedores              → Crear proveedor (RF_008)
 * - PUT /api/proveedores/{id}          → Editar proveedor (RF_009)
 * - DELETE /api/proveedores/{id}       → Eliminar lógico (RF_010)
 * - GET /api/proveedores               → Listar (RF_011)
 * - GET /api/proveedores/{id}          → Obtener por ID
 * - GET /api/proveedores/filtrar       → Filtrar por producto (RF_012)
 */
@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {
    
    private final ProveedorService proveedorService;
    
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }
    
    /**
     * Crea un nuevo proveedor (RF_008).
     * 
     * Ejemplo JSON:
     * {
     *   "nombre": "Proveedor ABC",
     *   "telefono": "1234567890",
     *   "correo": "contacto@proveedorabc.com",
     *   "direccion": "Calle Principal 123",
     *   "productosIds": ["prod1", "prod2"]
     * }
     */
    @PostMapping
    public ResponseEntity<ProveedorDtoResponse> crear(@Valid @RequestBody ProveedorDtoCreate proveedorDtoCreate) {
        ProveedorDtoResponse proveedor = proveedorService.crear(proveedorDtoCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedor);
    }
    
    /**
     * Actualiza un proveedor existente (RF_009).
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDtoResponse> actualizar(
            @PathVariable String id,
            @Valid @RequestBody ProveedorDtoUpdate proveedorDtoUpdate) {
        ProveedorDtoResponse proveedor = proveedorService.actualizar(id, proveedorDtoUpdate);
        return ResponseEntity.ok(proveedor);
    }
    
    /**
     * Elimina lógicamente un proveedor (RF_010).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        proveedorService.eliminarLogico(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Obtiene un proveedor por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDtoResponse> obtenerPorId(@PathVariable String id) {
        ProveedorDtoResponse proveedor = proveedorService.obtenerPorId(id);
        return ResponseEntity.ok(proveedor);
    }
    
    /**
     * Lista todos los proveedores activos (RF_011).
     */
    @GetMapping
    public ResponseEntity<List<ProveedorDtoResponse>> listar() {
        List<ProveedorDtoResponse> proveedores = proveedorService.listar();
        return ResponseEntity.ok(proveedores);
    }
    
    /**
     * Filtra proveedores por producto (RF_012).
     * Query parameter: ?productoId=id_del_producto
     */
    @GetMapping("/filtrar")
    public ResponseEntity<List<ProveedorDtoResponse>> filtrarPorProducto(
            @RequestParam String productoId) {
        List<ProveedorDtoResponse> proveedores = proveedorService.filtrarPorProducto(productoId);
        return ResponseEntity.ok(proveedores);
    }
}

