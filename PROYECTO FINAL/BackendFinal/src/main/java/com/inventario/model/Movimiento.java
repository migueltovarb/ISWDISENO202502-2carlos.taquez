package com.inventario.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad Movimiento que representa un movimiento de inventario (entrada o salida).
 * 
 * Reglas de negocio:
 * - cantidad > 0
 * - tipo debe ser "ENTRADA" o "SALIDA"
 * - debe estar ligado a un producto existente
 */
@Document(collection = "movimientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {
    
    @Id
    private String id;
    
    private String productoId;
    
    private String tipo; // "ENTRADA" o "SALIDA"
    
    private Integer cantidad;
    
    private String motivo;
    
    private LocalDateTime fechaMovimiento;
}

