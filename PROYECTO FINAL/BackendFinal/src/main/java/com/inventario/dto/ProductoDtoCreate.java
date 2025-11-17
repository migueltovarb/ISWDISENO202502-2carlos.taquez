package com.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para crear un nuevo producto.
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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDtoCreate {
    
    @NotBlank(message = "El código es obligatorio")
    private String codigo;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    private String descripcion;
    
    @NotNull(message = "La cantidad inicial es obligatoria")
    @Min(value = 0, message = "La cantidad inicial debe ser mayor o igual a 0")
    private Integer cantidadInicial;
    
    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(value = 0, message = "El stock mínimo debe ser mayor o igual a 0")
    private Integer stockMinimo;
    
    @NotNull(message = "El precio unitario es obligatorio")
    @Min(value = 0, message = "El precio unitario debe ser mayor o igual a 0")
    private Double precioUnitario;
}

