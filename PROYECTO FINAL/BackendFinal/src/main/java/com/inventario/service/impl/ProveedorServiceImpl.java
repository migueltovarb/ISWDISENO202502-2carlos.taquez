package com.inventario.service.impl;

import com.inventario.dto.ProveedorDtoCreate;
import com.inventario.dto.ProveedorDtoResponse;
import com.inventario.dto.ProveedorDtoUpdate;
import com.inventario.exception.EntityNotFoundException;
import com.inventario.model.Proveedor;
import com.inventario.repository.ProveedorRepository;
import com.inventario.service.ProveedorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de Proveedor.
 */
@Service
@Transactional
public class ProveedorServiceImpl implements ProveedorService {
    
    private final ProveedorRepository proveedorRepository;
    
    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }
    
    @Override
    public ProveedorDtoResponse crear(ProveedorDtoCreate proveedorDtoCreate) {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(proveedorDtoCreate.getNombre());
        proveedor.setTelefono(proveedorDtoCreate.getTelefono());
        proveedor.setCorreo(proveedorDtoCreate.getCorreo());
        proveedor.setDireccion(proveedorDtoCreate.getDireccion());
        proveedor.setProductosIds(proveedorDtoCreate.getProductosIds());
        proveedor.setActivo(true);
        
        Proveedor proveedorGuardado = proveedorRepository.save(proveedor);
        return convertirADto(proveedorGuardado);
    }
    
    @Override
    public ProveedorDtoResponse actualizar(String id, ProveedorDtoUpdate proveedorDtoUpdate) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + id));
        
        if (proveedorDtoUpdate.getNombre() != null) {
            proveedor.setNombre(proveedorDtoUpdate.getNombre());
        }
        if (proveedorDtoUpdate.getTelefono() != null) {
            proveedor.setTelefono(proveedorDtoUpdate.getTelefono());
        }
        if (proveedorDtoUpdate.getCorreo() != null) {
            proveedor.setCorreo(proveedorDtoUpdate.getCorreo());
        }
        if (proveedorDtoUpdate.getDireccion() != null) {
            proveedor.setDireccion(proveedorDtoUpdate.getDireccion());
        }
        if (proveedorDtoUpdate.getProductosIds() != null) {
            proveedor.setProductosIds(proveedorDtoUpdate.getProductosIds());
        }
        
        Proveedor proveedorActualizado = proveedorRepository.save(proveedor);
        return convertirADto(proveedorActualizado);
    }
    
    @Override
    public void eliminarLogico(String id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + id));
        
        proveedor.setActivo(false);
        proveedorRepository.save(proveedor);
    }
    
    @Override
    @Transactional(readOnly = true)
    public ProveedorDtoResponse obtenerPorId(String id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado con ID: " + id));
        
        return convertirADto(proveedor);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProveedorDtoResponse> listar() {
        return proveedorRepository.findByActivoTrue().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProveedorDtoResponse> filtrarPorProducto(String productoId) {
        return proveedorRepository.findByProductosIdsContains(productoId).stream()
                .filter(Proveedor::getActivo)
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }
    
    private ProveedorDtoResponse convertirADto(Proveedor proveedor) {
        ProveedorDtoResponse dto = new ProveedorDtoResponse();
        dto.setId(proveedor.getId());
        dto.setNombre(proveedor.getNombre());
        dto.setTelefono(proveedor.getTelefono());
        dto.setCorreo(proveedor.getCorreo());
        dto.setDireccion(proveedor.getDireccion());
        dto.setProductosIds(proveedor.getProductosIds());
        dto.setActivo(proveedor.getActivo());
        return dto;
    }
}

