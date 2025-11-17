package com.inventario.service.impl;

import com.inventario.dto.ProductoDtoCreate;
import com.inventario.dto.ProductoDtoResponse;
import com.inventario.dto.ProductoDtoUpdate;
import com.inventario.exception.BusinessException;
import com.inventario.exception.EntityNotFoundException;
import com.inventario.model.Producto;
import com.inventario.repository.ProductoRepository;
import com.inventario.service.AlertaService;
import com.inventario.service.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de Producto.
 */
@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {
    
    private final ProductoRepository productoRepository;
    private final AlertaService alertaService;
    
    public ProductoServiceImpl(ProductoRepository productoRepository, AlertaService alertaService) {
        this.productoRepository = productoRepository;
        this.alertaService = alertaService;
    }
    
    @Override
    public ProductoDtoResponse crear(ProductoDtoCreate productoDtoCreate) {
        // Verificar que el código no exista
        if (productoRepository.findByCodigo(productoDtoCreate.getCodigo()).isPresent()) {
            throw new BusinessException("Ya existe un producto con el código: " + productoDtoCreate.getCodigo());
        }
        
        Producto producto = new Producto();
        producto.setCodigo(productoDtoCreate.getCodigo());
        producto.setNombre(productoDtoCreate.getNombre());
        producto.setDescripcion(productoDtoCreate.getDescripcion());
        producto.setCantidadInicial(productoDtoCreate.getCantidadInicial());
        producto.setStockActual(productoDtoCreate.getCantidadInicial()); // Stock inicial = cantidad inicial
        producto.setStockMinimo(productoDtoCreate.getStockMinimo());
        producto.setPrecioUnitario(productoDtoCreate.getPrecioUnitario());
        producto.setActivo(true);
        producto.setFechaCreacion(LocalDateTime.now());
        producto.setFechaActualizacion(LocalDateTime.now());
        
        Producto productoGuardado = productoRepository.save(producto);
        
        // Verificar alertas después de crear
        verificarAlertas(productoGuardado.getId());
        
        return convertirADto(productoGuardado);
    }
    
    @Override
    public ProductoDtoResponse actualizar(String id, ProductoDtoUpdate productoDtoUpdate) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
        
        if (productoDtoUpdate.getNombre() != null) {
            producto.setNombre(productoDtoUpdate.getNombre());
        }
        if (productoDtoUpdate.getDescripcion() != null) {
            producto.setDescripcion(productoDtoUpdate.getDescripcion());
        }
        if (productoDtoUpdate.getStockMinimo() != null) {
            producto.setStockMinimo(productoDtoUpdate.getStockMinimo());
        }
        if (productoDtoUpdate.getPrecioUnitario() != null) {
            producto.setPrecioUnitario(productoDtoUpdate.getPrecioUnitario());
        }
        
        producto.setFechaActualizacion(LocalDateTime.now());
        Producto productoActualizado = productoRepository.save(producto);
        
        // Verificar alertas después de actualizar
        verificarAlertas(productoActualizado.getId());
        
        return convertirADto(productoActualizado);
    }
    
    @Override
    public void eliminarLogico(String id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
        
        producto.setActivo(false);
        producto.setFechaActualizacion(LocalDateTime.now());
        productoRepository.save(producto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProductoDtoResponse obtenerPorId(String id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
        
        return convertirADto(producto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductoDtoResponse> buscarPorCodigoOCriterio(String texto) {
        List<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(texto);
        
        // También buscar por código exacto si no se encontró nada
        productoRepository.findByCodigo(texto)
                .ifPresent(productos::add);
        
        return productos.stream()
                .filter(Producto::getActivo)
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductoDtoResponse> listar() {
        return productoRepository.findByActivoTrue().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductoDtoResponse> productosConStockBajo() {
        List<Producto> productos = productoRepository.findByActivoTrue();
        
        return productos.stream()
                .filter(p -> p.getStockActual() <= p.getStockMinimo())
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    @Override
    public void actualizarStockPorMovimiento(String productoId, String tipo, Integer cantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + productoId));
        
        if (tipo.equals("ENTRADA")) {
            producto.setStockActual(producto.getStockActual() + cantidad);
        } else if (tipo.equals("SALIDA")) {
            if (producto.getStockActual() < cantidad) {
                throw new BusinessException("Stock insuficiente. Stock actual: " + producto.getStockActual() + 
                        ", cantidad solicitada: " + cantidad);
            }
            producto.setStockActual(producto.getStockActual() - cantidad);
        } else {
            throw new BusinessException("Tipo de movimiento inválido: " + tipo);
        }
        
        producto.setFechaActualizacion(LocalDateTime.now());
        productoRepository.save(producto);
        
        // Verificar alertas después de actualizar stock
        verificarAlertas(productoId);
    }
    
    @Override
    public void verificarAlertas(String productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + productoId));
        
        alertaService.generarAlertasParaProducto(producto);
    }
    
    private ProductoDtoResponse convertirADto(Producto producto) {
        ProductoDtoResponse dto = new ProductoDtoResponse();
        dto.setId(producto.getId());
        dto.setCodigo(producto.getCodigo());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setCantidadInicial(producto.getCantidadInicial());
        dto.setStockActual(producto.getStockActual());
        dto.setStockMinimo(producto.getStockMinimo());
        dto.setPrecioUnitario(producto.getPrecioUnitario());
        dto.setActivo(producto.getActivo());
        dto.setFechaCreacion(producto.getFechaCreacion());
        dto.setFechaActualizacion(producto.getFechaActualizacion());
        return dto;
    }
}

