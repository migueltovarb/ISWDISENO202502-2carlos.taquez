package com.inventario.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad Usuario para autenticación básica.
 * Por ahora solo maneja CRUD, la autenticación se implementará después.
 * 
 * Roles posibles: "ENCARGADO", "ADMIN"
 */
@Document(collection = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String username;
    
    private String password;
    
    private String rol; // "ENCARGADO", "ADMIN"
    
    private Boolean activo;
}

