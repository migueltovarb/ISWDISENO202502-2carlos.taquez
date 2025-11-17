package com.inventario.exception;

/**
 * Excepción personalizada para cuando una entidad no es encontrada.
 */
public class EntityNotFoundException extends RuntimeException {
    
    public EntityNotFoundException(String message) {
        super(message);
    }
    
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

