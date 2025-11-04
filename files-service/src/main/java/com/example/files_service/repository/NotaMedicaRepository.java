package com.example.files_service.repository;

import com.example.files_service.entity.NotaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaMedicaRepository extends JpaRepository<NotaMedica, Long> {
    List<NotaMedica> findByPacienteNss(String pacienteNss);
}