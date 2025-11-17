package com.inventario.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad Alerta que representa una alerta del sistema de inventario.
 * 
 * Tipos de alerta:
 * - BAJO_STOCK: cuando el stock está por debajo del mínimo
 * - SIN_STOCK: cuando el stock llega a cero
 */
@Document(collection = "alertas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {
    
    @Id
    private String id;
    
    private String productoId;
    
    private String tipo; // "BAJO_STOCK", "SIN_STOCK", etc.
    
    private String mensaje;
    
    private LocalDateTime fechaHora;
    
    private Boolean critica;
    
    private Boolean atendida;
}

