package com.miempresa.vehiculosapi.exception;

public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException(String id) {
        super("Vehículo no encontrado con id: " + id);
    }

    public VehicleNotFoundException(String field, String value) {
        super("Vehículo no encontrado para " + field + ": " + value);
    }
}

