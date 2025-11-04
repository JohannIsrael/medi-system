package com.example.files_service.controllers;

import com.example.files_service.entities.dto.MedicalNoteRequest;
import com.example.files_service.entities.dto.MedicalNoteResponse;
import com.example.files_service.service.MedicalNoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expedientes/seguro")
public class MedicalNoteController {

    private final MedicalNoteService service;

    public MedicalNoteController(MedicalNoteService service) {
        this.service = service;
    }

    @PostMapping("/crear")
    public ResponseEntity<MedicalNoteResponse> create(@RequestBody MedicalNoteRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MedicalNoteResponse>> find(@RequestParam String nss) {
        return ResponseEntity.ok(service.findByNss(nss));
    }
}
