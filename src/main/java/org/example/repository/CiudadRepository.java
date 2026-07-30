package org.example.repository;

import org.example.model.Ciudad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CiudadRepository {

    private static final Logger logger =
            Logger.getLogger(CiudadRepository.class.getName());

    public List<Ciudad> listarPorDepartamento(int departamentoId) {

        List<Ciudad> lista = new ArrayList<>();

        String sql = "SELECT id, nombre, departamento_id " +
                "FROM ciudad WHERE departamento_id = ? " +
                "ORDER BY nombre";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, departamentoId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Ciudad ciudad = new Ciudad();
                ciudad.setId(rs.getInt("id"));
                ciudad.setNombre(rs.getString("nombre"));
                ciudad.setDepartamentoId(rs.getInt("departamento_id"));

                lista.add(ciudad);
            }

        } catch (Exception e) {
            logger.severe("Error listando ciudad por ID departamento: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return lista;
    }

}
