package com.inventario.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidad Proveedor que representa un proveedor de productos.
 * 
 * Reglas de negocio:
 * - correo debe tener formato válido (validado a nivel de servicio/DTO)
 */
@Document(collection = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {
    
    @Id
    private String id;
    
    private String nombre;
    
    private String telefono;
    
    private String correo;
    
    private String direccion;
    
    private List<String> productosIds; // IDs de productos que suministra
    
    private Boolean activo;
}

