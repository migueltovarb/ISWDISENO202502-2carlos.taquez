package com.miempresa.vehiculosapi.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.miempresa.vehiculosapi.dto.VehicleDto;
import com.miempresa.vehiculosapi.model.Vehicle;

public final class VehicleMapper {

    private VehicleMapper() {
        // Utility class
    }

    public static VehicleDto toDto(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }
        VehicleDto dto = new VehicleDto();
        dto.setId(vehicle.getId());
        dto.setBrand(vehicle.getBrand());
        dto.setModel(vehicle.getModel());
        dto.setYear(vehicle.getYear());
        dto.setPrice(vehicle.getPrice());
        dto.setColor(vehicle.getColor());
        dto.setKilometers(vehicle.getKilometers());
        dto.setType(vehicle.getType());
        return dto;
    }

    public static Vehicle toEntity(VehicleDto dto) {
        if (dto == null) {
            return null;
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setId(dto.getId());
        vehicle.setBrand(dto.getBrand());
        vehicle.setModel(dto.getModel());
        vehicle.setYear(dto.getYear());
        vehicle.setPrice(dto.getPrice());
        vehicle.setColor(dto.getColor());
        vehicle.setKilometers(dto.getKilometers());
        vehicle.setType(dto.getType());
        return vehicle;
    }

    public static List<VehicleDto> toDtoList(List<Vehicle> vehicles) {
        return vehicles.stream()
                .filter(Objects::nonNull)
                .map(VehicleMapper::toDto)
                .collect(Collectors.toList());
    }
}

