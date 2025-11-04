package com.example.files_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "nota_medica")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_nss", length = 50)
    private String pacienteNss;

    @Column(name = "id_doctor")
    private Integer idDoctor;

    @Column(name = "fecha_consulta")
    private LocalDate fechaConsulta;

    @Column(columnDefinition = "text")
    private String diagnostico;

    @Column(columnDefinition = "text")
    private String tratamiento;

    @Column(name = "is_confidential")
    private Boolean isConfidential = false;
}