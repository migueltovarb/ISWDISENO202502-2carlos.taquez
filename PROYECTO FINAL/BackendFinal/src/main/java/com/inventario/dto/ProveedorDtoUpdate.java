package com.inventario.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para actualizar un proveedor existente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDtoUpdate {
    
    private String nombre;
    
    private String telefono;
    
    @Email(message = "El correo debe tener un formato válido")
    private String correo;
    
    private String direccion;
    
    private List<String> productosIds;
}

