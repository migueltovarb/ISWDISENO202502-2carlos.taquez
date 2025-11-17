package com.inventario.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad Producto que representa un producto en el inventario.
 * 
 * Reglas de negocio:
 * - codigo debe ser único
 * - stockActual y cantidadInicial >= 0
 */
@Document(collection = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String codigo;
    
    private String nombre;
    
    private String descripcion;
    
    private Integer cantidadInicial;
    
    private Integer stockActual;
    
    private Integer stockMinimo;
    
    private Double precioUnitario;
    
    private Boolean activo;
    
    private LocalDateTime fechaCreacion;
    
    private LocalDateTime fechaActualizacion;
}

