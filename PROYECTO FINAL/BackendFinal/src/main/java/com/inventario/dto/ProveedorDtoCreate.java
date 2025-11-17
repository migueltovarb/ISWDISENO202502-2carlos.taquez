package com.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para crear un nuevo proveedor.
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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDtoCreate {
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    private String telefono;
    
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    private String correo;
    
    private String direccion;
    
    private List<String> productosIds;
}

