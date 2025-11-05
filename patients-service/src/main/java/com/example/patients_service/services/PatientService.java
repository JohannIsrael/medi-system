package com.example.patients_service.services;

import com.example.patients_service.dtos.PatientRequest;
import com.example.patients_service.dtos.PatientResponse;
import com.example.patients_service.dtos.PatientUpdateRequest;
import com.example.patients_service.entities.Patient;
import com.example.patients_service.repository.PatientRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PatientResponse create(PatientRequest request) {
    
        Patient patient = new Patient();
        patient.setNombre(request.getNombre());
        patient.setFecha_nacimiento(request.getFecha_nacimiento());
        patient.setNss(request.getNss());
        patient.setEmail(request.getEmail());
        patient.setPassword(request.getPassword());
        patient.setEs_doctor(request.getEs_doctor() != null ? request.getEs_doctor() : false);

        Patient saved = repository.save(patient);
        return toResponse(saved);
    }

    @Transactional
    public PatientResponse update(Long id, PatientUpdateRequest request) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        if (request.getNombre() != null) {
            patient.setNombre(request.getNombre());
        }
        if (request.getFecha_nacimiento() != null) {
            patient.setFecha_nacimiento(request.getFecha_nacimiento());
        }
        if (request.getEmail() != null) {
            // Validar que el email no esté en uso por otro paciente
            if (repository.existsByEmail(request.getEmail()) && 
                !request.getEmail().equals(patient.getEmail())) {
                throw new RuntimeException("El email ya está registrado");
            }
            patient.setEmail(request.getEmail());
        }
        if (request.getPassword() != null) {
            patient.setPassword(request.getPassword());
        }
        // Manejar es_doctor: si viene null, establecer false explícitamente
        if (request.getEs_doctor() != null) {
            patient.setEs_doctor(request.getEs_doctor());
        } else {
            // Si viene null, establecer false
            patient.setEs_doctor(false);
        }

        Patient updated = repository.save(patient);
        return toResponse(updated);
    }

    private PatientResponse toResponse(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getNombre(),
                patient.getFecha_nacimiento(),
                patient.getNss(),
                patient.getEmail(),
                patient.getEs_doctor()
        );
    }

    public List<PatientResponse> getPacientes() {
        List<Patient> patients = repository.findAll();
        return patients.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}

