package com.example.files_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "medical_notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idPaciente;
    private Long idDoctor;
    private String nssPaciente;
    private LocalDateTime fechaConsulta;
    private String diagnostico;
    private String tratamiento;
}