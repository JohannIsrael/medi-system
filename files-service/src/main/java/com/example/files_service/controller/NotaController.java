package com.example.files_service.controller;

import com.example.files_service.entity.NotaMedica;
import com.example.files_service.repository.NotaMedicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expedientes")
@RequiredArgsConstructor
public class NotaController {

    private final NotaMedicaRepository repo;
    private final JdbcTemplate jdbc;

    // ---------- INSEGURO: SQL Injection (concatenación) ----------
    // GET /api/expedientes/inseguro/buscar?nss=<valor>
    @GetMapping("/inseguro/buscar")
    public ResponseEntity<List<Map<String, Object>>> buscarInseguro(@RequestParam String nss) {
        // Vulnerable: concatenación directa => SQLi posible
        String sql = "SELECT id, paciente_nss, id_doctor, fecha_consulta, diagnostico, tratamiento, is_confidential "
                   + "FROM nota_medica WHERE paciente_nss = '" + nss + "';";
        List<Map<String, Object>> rows = jdbc.queryForList(sql);
        return ResponseEntity.ok(rows);
    }

    // ---------- SEGURO: ORM parametrizado ----------
    // GET /api/expedientes/seguro/buscar?nss=<valor>
    @GetMapping("/seguro/buscar")
    public ResponseEntity<List<NotaMedica>> buscarSeguro(@RequestParam String nss) {
        List<NotaMedica> notas = repo.findByPacienteNss(nss);
        return ResponseEntity.ok(notas);
    }

    // ---------- INSEGURO: Mass assignment / update dinámico ----------
    // PUT /api/expedientes/inseguro/{id}
    @PutMapping("/inseguro/{id}")
    public ResponseEntity<Void> actualizarInseguro(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        // vulnerable: arma SQL UPDATE con los keys del JSON -> permite cambiar is_confidential, id_doctor, etc.
        if (payload == null || payload.isEmpty()) return ResponseEntity.noContent().build();

        StringBuilder sql = new StringBuilder("UPDATE nota_medica SET ");
        payload.forEach((k, v) -> {
            sql.append(k).append(" = ");
            if (v == null) {
                sql.append("NULL,");
            } else {
                // simple quoting (vulnerable en sí mismo)
                sql.append("'").append(v.toString().replace("'", "''")).append("',");
            }
        });
        sql.setLength(sql.length() - 1); // quita la coma final
        sql.append(" WHERE id = ").append(id);
        jdbc.update(sql.toString());
        return ResponseEntity.noContent().build();
    }

    // ---------- SEGURO: Mass assignment mitigado (whitelist) ----------
    // PUT /api/expedientes/seguro/{id}
    @PutMapping("/seguro/{id}")
    public ResponseEntity<NotaMedica> actualizarSeguro(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        NotaMedica nota = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Nota no encontrada"));
        // whitelist: solo permitimos diagnostico y tratamiento
        if (payload.containsKey("diagnostico")) {
            nota.setDiagnostico((String) payload.get("diagnostico"));
        }
        if (payload.containsKey("tratamiento")) {
            nota.setTratamiento((String) payload.get("tratamiento"));
        }
        // NO permitimos cambiar idDoctor ni isConfidential desde el payload
        NotaMedica saved = repo.save(nota);
        return ResponseEntity.ok(saved);
    }

    // ---------- Aux: crear nota (para pruebas) ----------
    // POST /api/expedientes/
    @PostMapping("/")
    public ResponseEntity<NotaMedica> crearNota(@RequestBody NotaMedica body) {
        if (body.getFechaConsulta() == null) body.setFechaConsulta(LocalDate.now());
        NotaMedica saved = repo.save(body);
        return ResponseEntity.ok(saved);
    }
}
