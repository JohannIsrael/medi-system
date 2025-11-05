package com.example.patients_service.dtos;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PatientUpdateRequest {
    private String nombre;
    private LocalDate fecha_nacimiento;
    private String email;
    private String password;
    private Boolean es_doctor;
}

