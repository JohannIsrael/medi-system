package com.example.files_service.repository;

import com.example.files_service.entities.MedicalNote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicalNoteRepository extends JpaRepository<MedicalNote, Long> {
    List<MedicalNote> findByNssPacienteOrderByFechaConsultaDesc(String nssPaciente);
}