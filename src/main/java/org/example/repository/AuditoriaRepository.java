package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AuditoriaRepository {

    public void registrar(String usuario, String accion) {

        String sql = "INSERT INTO auditoria(usuario, accion) VALUES(?,?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, accion);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
