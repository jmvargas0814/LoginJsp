package org.example.repository;

import org.example.model.Departamento;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DepartamentoRepository {

    private static final Logger logger =
            Logger.getLogger(DepartamentoRepository.class.getName());

    public List<Departamento> listar() {

        List<Departamento> lista = new ArrayList<>();

        String sql = "SELECT id, nombre FROM departamento ORDER BY nombre";

        try (Connection conn = Conexion.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Departamento dep = new Departamento();
                dep.setId(rs.getInt("id"));
                dep.setNombre(rs.getString("nombre"));

                lista.add(dep);
            }

        } catch (Exception e) {
            logger.severe("Error listando departamentos: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return lista;
    }
}
