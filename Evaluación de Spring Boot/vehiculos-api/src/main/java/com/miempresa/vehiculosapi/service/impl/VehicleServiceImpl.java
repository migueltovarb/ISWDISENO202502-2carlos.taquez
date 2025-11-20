package com.miempresa.vehiculosapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.miempresa.vehiculosapi.dto.VehicleDto;
import com.miempresa.vehiculosapi.exception.VehicleNotFoundException;
import com.miempresa.vehiculosapi.mapper.VehicleMapper;
import com.miempresa.vehiculosapi.model.Vehicle;
import com.miempresa.vehiculosapi.repository.VehicleRepository;
import com.miempresa.vehiculosapi.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        return VehicleMapper.toDtoList(vehicleRepository.findAll());
    }

    @Override
    public VehicleDto getVehicleById(String id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
        return VehicleMapper.toDto(vehicle);
    }

    @Override
    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = VehicleMapper.toEntity(vehicleDto);
        vehicle.setId(null);
        Vehicle saved = vehicleRepository.save(vehicle);
        return VehicleMapper.toDto(saved);
    }

    @Override
    public VehicleDto updateVehicle(String id, VehicleDto vehicleDto) {
        Vehicle existing = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
        existing.setBrand(vehicleDto.getBrand());
        existing.setModel(vehicleDto.getModel());
        existing.setYear(vehicleDto.getYear());
        existing.setPrice(vehicleDto.getPrice());
        existing.setColor(vehicleDto.getColor());
        existing.setKilometers(vehicleDto.getKilometers());
        existing.setType(vehicleDto.getType());
        Vehicle updated = vehicleRepository.save(existing);
        return VehicleMapper.toDto(updated);
    }

    @Override
    public void deleteVehicle(String id) {
        Vehicle existing = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
        vehicleRepository.delete(existing);
    }

    @Override
    public List<VehicleDto> getVehiclesByBrand(String brand) {
        return VehicleMapper.toDtoList(vehicleRepository.findByBrandIgnoreCase(brand));
    }
}

