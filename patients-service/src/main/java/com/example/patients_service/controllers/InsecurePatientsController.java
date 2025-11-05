package com.example.patients_service.controllers;

import com.example.patients_service.services.InsecurePatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pacientes/inseguro")
public class InsecurePatientsController {

    private final InsecurePatientService insecurePatientService;

    public InsecurePatientsController(InsecurePatientService insecurePatientService) {
        this.insecurePatientService = insecurePatientService;
    }

    @PostMapping("/registro")
    public ResponseEntity<Map<String, Object>> registroInseguro(@RequestBody Map<String, Object> body) {
        Map<String, Object> pacienteCreado = insecurePatientService.createInsecure(body);
        return ResponseEntity.ok(pacienteCreado);
    }

    @PutMapping("/perfil/{id}")
    public ResponseEntity<Map<String, Object>> actualizarPerfilInseguro(
            @PathVariable("id") String id, 
            @RequestBody Map<String, Object> body) {
        System.out.println("ID recibido en controller: " + id);
        Map<String, Object> pacienteActualizado = insecurePatientService.updateInsecure(id, body);
        return ResponseEntity.ok(pacienteActualizado);
    }
}
