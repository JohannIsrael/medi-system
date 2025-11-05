package com.example.patients_service.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class InsecurePatientService {

    private final JdbcTemplate jdbcTemplate;

    public InsecurePatientService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> createInsecure(Map<String, Object> body) {

        System.out.println("createInsecure");


        String nombre = body.get("nombre") != null ? body.get("nombre").toString() : null;
        String fechaNacimiento = body.get("fecha_nacimiento") != null ? body.get("fecha_nacimiento").toString() : null;
        String nss = body.get("nss") != null ? body.get("nss").toString() : null;
        String email = body.get("email") != null ? body.get("email").toString() : null;
        String password = body.get("password") != null ? body.get("password").toString() : null;
        
        Object esDoctorObj = body.get("es_doctor");
        boolean esDoctor = false;
        if (esDoctorObj != null) {
            if (esDoctorObj instanceof Boolean) {
                esDoctor = (Boolean) esDoctorObj;
            } else if (esDoctorObj instanceof String) {
                esDoctor = Boolean.parseBoolean((String) esDoctorObj);
            }
        }

        // SQL injection
        String sql = "INSERT INTO pacientes (nombre, fecha_nacimiento, nss, email, password, es_doctor) " +
                     "VALUES ('" + nombre + "', '" + fechaNacimiento + "', '" + nss + "', '" + 
                     email + "', '" + password + "', " + esDoctor + ")";

        System.out.println("sql: " + sql);

        jdbcTemplate.update(sql);

        // Obtener el registro completo creado usando email (sin validar que sea único)
        String selectSql = "SELECT * FROM pacientes WHERE email = '" + email + "'";
        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(selectSql);
        
        // Retornar el primer resultado sin validar que sea único
        Map<String, Object> pacienteCreado = resultados.isEmpty() ? null : resultados.get(0);

        System.out.println("Paciente creado: " + pacienteCreado);
        
        return pacienteCreado;
    }

    public Map<String, Object> updateInsecure(String id, Map<String, Object> body) {
        StringBuilder sql = new StringBuilder("UPDATE pacientes SET ");
        boolean first = true;

        if (body.containsKey("nombre")) {
            sql.append("nombre = '").append(body.get("nombre")).append("'");
            first = false;
        }
        if (body.containsKey("fecha_nacimiento")) {
            if (!first) sql.append(", ");
            sql.append("fecha_nacimiento = '").append(body.get("fecha_nacimiento")).append("'");
            first = false;
        }
        if (body.containsKey("email")) {
            if (!first) sql.append(", ");
            sql.append("email = '").append(body.get("email")).append("'");
            first = false;
        }
        if (body.containsKey("password")) {
            if (!first) sql.append(", ");
            sql.append("password = '").append(body.get("password")).append("'");
            first = false;
        }

        // Convertir id a String para uso en SQL (sin validación de tipo)
        String idStr = id != null ? id.toString() : "";

        System.out.println("idStr: " + idStr);
        sql.append(" WHERE id = ").append(idStr);
        System.out.println("sql INSECURE: " + sql.toString());

        // Realiza accion SQL de forma insegura
        jdbcTemplate.update(sql.toString());

        // Obtener el registro completo actualizado (sin validar que sea único)
        String selectSql = "SELECT * FROM pacientes WHERE id = " + idStr;
        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(selectSql);
        
        // Retornar el primer resultado sin validar que sea único
        return resultados.isEmpty() ? null : resultados.get(0);
    }
}

