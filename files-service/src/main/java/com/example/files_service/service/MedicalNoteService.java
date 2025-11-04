package com.example.files_service.service;

import com.example.files_service.entities.MedicalNote;
import com.example.files_service.entities.dto.MedicalNoteRequest;
import com.example.files_service.entities.dto.MedicalNoteResponse;
import com.example.files_service.repository.MedicalNoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalNoteService {

    private final MedicalNoteRepository repository;

    public MedicalNoteService(MedicalNoteRepository repository) {
        this.repository = repository;
    }

    public MedicalNoteResponse create(MedicalNoteRequest request) {
        MedicalNote note = new MedicalNote();
        note.setIdPaciente(request.getIdPaciente());
        note.setIdDoctor(request.getIdDoctor());
        note.setNssPaciente(request.getNssPaciente());
        note.setFechaConsulta(request.getFechaConsulta());
        note.setDiagnostico(request.getDiagnostico());
        note.setTratamiento(request.getTratamiento());

        MedicalNote saved = repository.save(note);
        return toResponse(saved);
    }

    public List<MedicalNoteResponse> findByNss(String nss) {
        return repository.findByNssPacienteOrderByFechaConsultaDesc(nss)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private MedicalNoteResponse toResponse(MedicalNote note) {
        return new MedicalNoteResponse(
                note.getId(),
                note.getIdPaciente(),
                note.getIdDoctor(),
                note.getNssPaciente(),
                note.getFechaConsulta(),
                note.getDiagnostico(),
                note.getTratamiento()
        );
    }
}
