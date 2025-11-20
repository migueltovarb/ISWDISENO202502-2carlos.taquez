package com.miempresa.vehiculosapi.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miempresa.vehiculosapi.dto.VehicleDto;
import com.miempresa.vehiculosapi.model.Vehicle;
import com.miempresa.vehiculosapi.repository.VehicleRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VehicleRepository vehicleRepository;

    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        vehicle = new Vehicle("abc123", "Ford", "Ranger", 2024, BigDecimal.valueOf(38000), "Negro", 0, "Pickup");
    }

    @Test
    void shouldReturnVehiclesList() throws Exception {
        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle));

        mockMvc.perform(get("/api/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].brand", is("Ford")));
    }

    @Test
    void shouldCreateVehicle() throws Exception {
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

    VehicleDto dto = new VehicleDto(null, "Ford", "Ranger", 2024, BigDecimal.valueOf(38000), "Negro", 0, "Pickup");

        mockMvc.perform(post("/api/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.brand", is("Ford")));
    }
}

