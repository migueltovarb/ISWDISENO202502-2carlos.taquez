package com.miempresa.vehiculosapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.miempresa.vehiculosapi.dto.VehicleDto;
import com.miempresa.vehiculosapi.exception.VehicleNotFoundException;
import com.miempresa.vehiculosapi.model.Vehicle;
import com.miempresa.vehiculosapi.repository.VehicleRepository;
import com.miempresa.vehiculosapi.service.impl.VehicleServiceImpl;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    private Vehicle vehicle;
    private VehicleDto vehicleDto;

    @BeforeEach
    void setUp() {
    vehicle = new Vehicle("1", "Toyota", "Corolla", 2023, BigDecimal.valueOf(25000), "Azul", 10, "Sedan");

    vehicleDto = new VehicleDto("1", "Toyota", "Corolla", 2023, BigDecimal.valueOf(25000), "Azul", 10, "Sedan");
    }

    @Test
    void getVehicleById_shouldReturnDto_whenVehicleExists() {
        when(vehicleRepository.findById("1")).thenReturn(Optional.of(vehicle));

        VehicleDto result = vehicleService.getVehicleById("1");

        assertThat(result.getBrand()).isEqualTo("Toyota");
        verify(vehicleRepository).findById("1");
    }

    @Test
    void getVehicleById_shouldThrowException_whenVehicleNotFound() {
        when(vehicleRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class, () -> vehicleService.getVehicleById("1"));
    }

    @Test
    void createVehicle_shouldPersistAndReturnDto() {
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        VehicleDto result = vehicleService.createVehicle(vehicleDto);

        assertThat(result.getId()).isEqualTo("1");
        verify(vehicleRepository).save(any(Vehicle.class));
    }
}

