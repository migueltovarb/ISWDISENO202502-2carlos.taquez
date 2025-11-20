package com.miempresa.vehiculosapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI vehicleOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API CRUD de Vehículos")
                        .description("API REST con Spring Boot y MongoDB Atlas para gestión de vehículos.")
                        .version("1.0.0"));
    }
}

