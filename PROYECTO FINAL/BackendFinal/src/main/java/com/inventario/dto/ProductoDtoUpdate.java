package com.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para actualizar un producto existente.
 * 
 * Ejemplo JSON:
 * {
 *   "nombre": "Laptop Dell Actualizada",
 *   "descripcion": "Descripción actualizada",
 *   "stockMinimo": 15,
 *   "precioUnitario": 16000.00
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDtoUpdate {
    
    private String nombre;
    
    private String descripcion;
    
    @Min(value = 0, message = "El stock mínimo debe ser mayor o igual a 0")
    private Integer stockMinimo;
    
    @Min(value = 0, message = "El precio unitario debe ser mayor o igual a 0")
    private Double precioUnitario;
}

