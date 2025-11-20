package com.miempresa.vehiculosapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.miempresa.vehiculosapi.model.Vehicle;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {

    List<Vehicle> findByBrandIgnoreCase(String brand);
}

