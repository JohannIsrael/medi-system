package com.example.files_service.controllers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expedientes/inseguro")
public class InsecureMedicalNoteController {

    private final JdbcTemplate jdbc;

    public InsecureMedicalNoteController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Map<String, Object>>> find(@RequestParam String nss) {
        String sql = "SELECT * FROM medical_notes WHERE nss_paciente = '" + nss + "' ORDER BY fecha_consulta DESC";
        return ResponseEntity.ok(jdbc.queryForList(sql));
    }

    @PostMapping("/crear")
    public ResponseEntity<Integer> create(@RequestBody Map<String, Object> body) {
        String sql = "INSERT INTO medical_notes (id_paciente, id_doctor, nss_paciente, fecha_consulta, diagnostico, tratamiento) " +
                     "VALUES (" + body.get("idPaciente") + ", " + body.get("idDoctor") + ", '" + body.get("nssPaciente") + "', '" +
                     body.get("fechaConsulta") + "', '" + body.get("diagnostico") + "', '" + body.get("tratamiento") + "')";
        return ResponseEntity.ok(jdbc.update(sql));
    }
}
