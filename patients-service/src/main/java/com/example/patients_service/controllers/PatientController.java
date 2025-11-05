package com.example.patients_service.controllers;

import com.example.patients_service.dtos.PatientRequest;
import com.example.patients_service.dtos.PatientResponse;
import com.example.patients_service.dtos.PatientUpdateRequest;
import com.example.patients_service.services.PatientService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes/seguro")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World - Patients Service");
    }

    @PostMapping("/registro")
    public ResponseEntity<PatientResponse> registroSeguro(@RequestBody PatientRequest request) {
        PatientResponse response = patientService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/perfil/{id}")
    public ResponseEntity<PatientResponse> actualizarPerfilSeguro(
            @PathVariable Long id, 
            @RequestBody PatientUpdateRequest request) {
        PatientResponse response = patientService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pacientes")
    public ResponseEntity<List<PatientResponse>> getPacientes() {
        List<PatientResponse> response = patientService.getPacientes();
        return ResponseEntity.ok(response);
    }
}

