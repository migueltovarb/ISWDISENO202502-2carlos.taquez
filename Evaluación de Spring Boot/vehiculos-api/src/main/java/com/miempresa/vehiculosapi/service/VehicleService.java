package com.miempresa.vehiculosapi.service;

import java.util.List;

import com.miempresa.vehiculosapi.dto.VehicleDto;

public interface VehicleService {

    List<VehicleDto> getAllVehicles();

    VehicleDto getVehicleById(String id);

    VehicleDto createVehicle(VehicleDto vehicleDto);

    VehicleDto updateVehicle(String id, VehicleDto vehicleDto);

    void deleteVehicle(String id);

    List<VehicleDto> getVehiclesByBrand(String brand);
}

