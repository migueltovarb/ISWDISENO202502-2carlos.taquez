package com.inventario.service.impl;

import com.inventario.dto.MovimientoDto;
import com.inventario.exception.BusinessException;
import com.inventario.model.Movimiento;
import com.inventario.repository.MovimientoRepository;
import com.inventario.service.MovimientoService;
import com.inventario.service.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de Movimiento.
 */
@Service
@Transactional
public class MovimientoServiceImpl implements MovimientoService {
    
    private final MovimientoRepository movimientoRepository;
    private final ProductoService productoService;
    
    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, 
                                  ProductoService productoService) {
        this.movimientoRepository = movimientoRepository;
        this.productoService = productoService;
    }
    
    @Override
    public MovimientoDto registrarEntrada(String productoId, Integer cantidad, String motivo) {
        if (cantidad <= 0) {
            throw new BusinessException("La cantidad debe ser mayor a 0");
        }
        
        // Actualizar stock del producto
        productoService.actualizarStockPorMovimiento(productoId, "ENTRADA", cantidad);
        
        // Crear movimiento
        Movimiento movimiento = new Movimiento();
        movimiento.setProductoId(productoId);
        movimiento.setTipo("ENTRADA");
        movimiento.setCantidad(cantidad);
        movimiento.setMotivo(motivo);
        movimiento.setFechaMovimiento(LocalDateTime.now());
        
        Movimiento movimientoGuardado = movimientoRepository.save(movimiento);
        return convertirADto(movimientoGuardado);
    }
    
    @Override
    public MovimientoDto registrarSalida(String productoId, Integer cantidad, String motivo) {
        if (cantidad <= 0) {
            throw new BusinessException("La cantidad debe ser mayor a 0");
        }
        
        // Actualizar stock del producto (verifica stock suficiente)
        productoService.actualizarStockPorMovimiento(productoId, "SALIDA", cantidad);
        
        // Crear movimiento
        Movimiento movimiento = new Movimiento();
        movimiento.setProductoId(productoId);
        movimiento.setTipo("SALIDA");
        movimiento.setCantidad(cantidad);
        movimiento.setMotivo(motivo);
        movimiento.setFechaMovimiento(LocalDateTime.now());
        
        Movimiento movimientoGuardado = movimientoRepository.save(movimiento);
        return convertirADto(movimientoGuardado);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MovimientoDto> listarPorProducto(String productoId) {
        return movimientoRepository.findByProductoId(productoId).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MovimientoDto> listarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return movimientoRepository.findByFechaMovimientoBetween(inicio, fin).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MovimientoDto> listarTodos() {
        return movimientoRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    private MovimientoDto convertirADto(Movimiento movimiento) {
        MovimientoDto dto = new MovimientoDto();
        dto.setId(movimiento.getId());
        dto.setProductoId(movimiento.getProductoId());
        dto.setTipo(movimiento.getTipo());
        dto.setCantidad(movimiento.getCantidad());
        dto.setMotivo(movimiento.getMotivo());
        dto.setFechaMovimiento(movimiento.getFechaMovimiento());
        return dto;
    }
}

