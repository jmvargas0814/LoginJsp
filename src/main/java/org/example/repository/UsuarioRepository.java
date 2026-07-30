package org.example.repository;

import org.example.model.Usuario;
import org.example.utils.UsuarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class UsuarioRepository implements UsuarioDAO {

    private static final Logger logger =
            Logger.getLogger(UsuarioRepository.class.getName());

    @Override
    public Usuario login(String username) {

        String sql = "SELECT * FROM usuario WHERE username=? AND activo=TRUE";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password")); // IMPORTANTE
                u.setRol(rs.getString("rol"));
                u.setCambiarPassword(rs.getBoolean("cambiar_password"));

                return u;
            }

        } catch (Exception e) {
            logger.severe("Error login: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return null;
    }

    public void guardar(Usuario usuario) {

        String sql = "INSERT INTO usuario(" +
                "username, password, numero_documento, nombre, apellido, " +
                "email, telefono, genero, rol, activo, departamento_id, ciudad_id" +
                ") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getNumeroDocumento());
            ps.setString(4, usuario.getNombre());
            ps.setString(5, usuario.getApellido());
            ps.setString(6, usuario.getEmail());
            ps.setString(7, usuario.getTelefono());
            ps.setString(8, usuario.getGenero());
            ps.setString(9, usuario.getRol());
            ps.setBoolean(10, usuario.isActivo());
            ps.setInt(11, usuario.getDepartamentoId());
            ps.setInt(12, usuario.getCiudadId());

            ps.executeUpdate();

        } catch (Exception e) {
            logger.severe("Error guardando usuario: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean existeDocumento(String documento) {

        String sql = "SELECT 1 FROM usuario WHERE numero_documento=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, documento);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            logger.severe("Error consultando usuario existente: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
