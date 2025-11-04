package com.example.patients_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pacientes")
public class PatientController {

    @GetMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World - Patients Service");
    }

    @PostMapping("/inseguro/registro")
    public ResponseEntity<Map<String, String>> registroInseguro() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registro inseguro - Endpoint activo");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/seguro/registro")
    public ResponseEntity<Map<String, String>> registroSeguro() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registro seguro - Endpoint activo");
        return ResponseEntity.ok(response);
    }

    
    @PutMapping("/inseguro/perfil/{id}")
    public ResponseEntity<Map<String, String>> actualizarPerfilInseguro(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Actualización de perfil inseguro - Endpoint activo");
        response.put("id", String.valueOf(id));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/seguro/perfil/{id}")
    public ResponseEntity<Map<String, String>> actualizarPerfilSeguro(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Actualización de perfil seguro - Endpoint activo");
        response.put("id", String.valueOf(id));
        return ResponseEntity.ok(response);
    }
}

