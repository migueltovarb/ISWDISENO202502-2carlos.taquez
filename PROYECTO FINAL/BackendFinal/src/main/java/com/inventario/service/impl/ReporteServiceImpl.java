package com.inventario.service.impl;

import com.inventario.dto.AlertaDtoResponse;
import com.inventario.dto.MovimientoDto;
import com.inventario.dto.ProductoDtoResponse;
import com.inventario.dto.ReporteInventarioDto;
import com.inventario.service.AlertaService;
import com.inventario.service.MovimientoService;
import com.inventario.service.ProductoService;
import com.inventario.service.ReporteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementación del servicio de Reportes.
 */
@Service
@Transactional(readOnly = true)
public class ReporteServiceImpl implements ReporteService {
    
    private final ProductoService productoService;
    private final AlertaService alertaService;
    private final MovimientoService movimientoService;
    
    public ReporteServiceImpl(ProductoService productoService, 
                              AlertaService alertaService,
                              MovimientoService movimientoService) {
        this.productoService = productoService;
        this.alertaService = alertaService;
        this.movimientoService = movimientoService;
    }
    
    @Override
    public ReporteInventarioDto reporteGeneralInventario() {
        List<ProductoDtoResponse> productos = productoService.listar();
        List<ProductoDtoResponse> productosStockBajo = productoService.productosConStockBajo();
        List<AlertaDtoResponse> alertasActivas = alertaService.listarAlertasActivas();
        List<AlertaDtoResponse> alertasCriticas = alertaService.listarAlertasCriticas();
        
        ReporteInventarioDto reporte = new ReporteInventarioDto();
        reporte.setTotalProductos(productos.size());
        reporte.setProductosActivos(productos.size()); // Solo contamos activos en listar()
        reporte.setProductosConStockBajo(productosStockBajo.size());
        reporte.setTotalAlertasActivas(alertasActivas.size());
        reporte.setAlertasCriticas(alertasCriticas.size());
        reporte.setProductos(productos);
        reporte.setAlertas(alertasActivas);
        
        return reporte;
    }
    
    @Override
    public List<MovimientoDto> reporteMovimientos(LocalDateTime inicio, LocalDateTime fin) {
        return movimientoService.listarPorRangoFechas(inicio, fin);
    }
}

