package com.example.patients_service.dtos;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PatientResponse {
    private Long id;
    private String nombre;
    private LocalDate fecha_nacimiento;
    private String nss;
    private String email;
    private Boolean es_doctor;

    public PatientResponse(Long id, String nombre, LocalDate fecha_nacimiento, 
                          String nss, String email, Boolean es_doctor) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nss = nss;
        this.email = email;
        this.es_doctor = es_doctor;
    }
}

