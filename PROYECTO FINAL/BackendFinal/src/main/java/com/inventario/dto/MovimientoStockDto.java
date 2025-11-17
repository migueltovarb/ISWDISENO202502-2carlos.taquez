package com.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para registrar entrada o salida de stock.
 * 
 * Ejemplo JSON para entrada:
 * {
 *   "cantidad": 50,
 *   "motivo": "Compra de proveedor"
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoStockDto {
    
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;
    
    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;
}

