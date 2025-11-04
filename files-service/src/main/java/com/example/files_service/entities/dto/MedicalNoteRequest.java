package com.example.files_service.entities.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MedicalNoteRequest {
    private Long idPaciente;
    private Long idDoctor;
    private String nssPaciente;
    private LocalDateTime fechaConsulta;
    private String diagnostico;
    private String tratamiento;

}