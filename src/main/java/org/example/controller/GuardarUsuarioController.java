package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exception.BusinessException;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/guardarUsuario")
public class GuardarUsuarioController extends HttpServlet {

    private UsuarioService usuarioService = new UsuarioService();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String numeroDocumento = request.getParameter("numeroDocumento");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String genero = request.getParameter("genero");
        String rol = request.getParameter("rol");

        boolean activo = request.getParameter("activo") != null;

        int departamentoId = Integer.parseInt(request.getParameter("departamentoId"));
        int ciudadId = Integer.parseInt(request.getParameter("ciudadId"));

        // ✅ Generar username automáticamente
        String username = generarUsername(nombre, apellido);

        // ✅ Password = número documento
        String password = numeroDocumento;

        Usuario usuario = new Usuario();

        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setNumeroDocumento(numeroDocumento);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setGenero(genero);
        usuario.setRol(rol);
        usuario.setActivo(activo);
        usuario.setDepartamentoId(departamentoId);
        usuario.setCiudadId(ciudadId);
        try {

            usuarioService.guardar(usuario);

            request.setAttribute("success",
                    "Usuario creado correctamente. Usuario generado: " + username);

            request.getRequestDispatcher("/WEB-INF/jsp/crearUsuario.jsp")
                    .forward(request, response);

        } catch (BusinessException ex) {

            request.setAttribute("error", ex.getMessage());

        } catch (Exception ex) {

            request.setAttribute("error", "Error interno del sistema");
        }

    }
    private String generarUsername(String nombre, String apellido) {

        String primerNombre = Arrays.stream(nombre.trim().split(" "))
                .filter(s -> !s.isEmpty())
                .findFirst()
                .orElse("");

        String primerApellido = Arrays.stream(apellido.trim().split(" "))
                .filter(s -> !s.isEmpty())
                .findFirst()
                .orElse("");

        return (primerNombre + "." + primerApellido).toLowerCase();
    }

}