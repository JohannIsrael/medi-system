package com.example.gateway_service.config;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.web.servlet.function.RequestPredicates.path;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> patientsRoute() {
        return GatewayRouterFunctions.route("patients-service")
                .route(path("/api-pacientes").or(path("/api-pacientes/**")), 
                    request -> {
                        // El gateway recibe /api-pacientes o /api-pacientes/** y lo redirige a /api/pacientes/**
                        String requestPath = request.path();
                        String servicePath = requestPath.replaceFirst("/api-pacientes", "/api/pacientes");
                        // Si es exactamente /api/pacientes (sin barra ni subrutas), agregar barra final
                        if (servicePath.equals("/api/pacientes")) {
                            servicePath = servicePath + "/";
                        }
                        URI uri = URI.create("http://localhost:4001" + servicePath);
                        return HandlerFunctions.http(uri).handle(request);
                    })
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> filesRoute() {
        return GatewayRouterFunctions.route("files-service")
                .route(path("/api-expedientes").or(path("/api-expedientes/**")), 
                    request -> {
                        // El gateway recibe /api-expedientes o /api-expedientes/** y lo redirige a /api/expedientes/**
                        String requestPath = request.path();
                        String servicePath = requestPath.replaceFirst("/api-expedientes", "/api/expedientes");
                        // Si es exactamente /api/expedientes (sin barra ni subrutas), agregar barra final
                        if (servicePath.equals("/api/expedientes")) {
                            servicePath = servicePath + "/";
                        }
                        URI uri = URI.create("http://localhost:4002" + servicePath);
                        return HandlerFunctions.http(uri).handle(request);
                    })
                .build();
    }
}

