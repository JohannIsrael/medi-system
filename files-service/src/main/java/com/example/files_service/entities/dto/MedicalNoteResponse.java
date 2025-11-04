package com.example.files_service.entities.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MedicalNoteResponse {
    private Long id;
    private Long idPaciente;
    private Long idDoctor;
    private String nssPaciente;
    private LocalDateTime fechaConsulta;
    private String diagnostico;
    private String tratamiento;

    public MedicalNoteResponse(Long id, Long idPaciente, Long idDoctor,
                               String nssPaciente, LocalDateTime fechaConsulta,
                               String diagnostico, String tratamiento) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idDoctor = idDoctor;
        this.nssPaciente = nssPaciente;
        this.fechaConsulta = fechaConsulta;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }

}
