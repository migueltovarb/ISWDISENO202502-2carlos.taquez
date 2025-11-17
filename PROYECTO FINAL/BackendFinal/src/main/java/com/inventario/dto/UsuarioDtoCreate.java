package com.inventario.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para crear un nuevo usuario.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDtoCreate {
    
    @NotBlank(message = "El username es obligatorio")
    private String username;
    
    @NotBlank(message = "El password es obligatorio")
    @Size(min = 6, message = "El password debe tener al menos 6 caracteres")
    private String password;
    
    @NotBlank(message = "El rol es obligatorio")
    private String rol;
}

