package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.repository.DepartamentoRepository;
import org.example.repository.CiudadRepository;
import org.example.model.Departamento;
import org.example.model.Ciudad;

import java.io.IOException;
import java.util.List;

@WebServlet("/crearUsuario")
public class UsuarioController extends HttpServlet {

    private DepartamentoRepository departamentoRepository = new DepartamentoRepository();
    private CiudadRepository ciudadRepository = new CiudadRepository();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ 1. Consultar datos
        List<Departamento> lista = departamentoRepository.listar();

        // ✅ 2. Enviar datos a la vista
        request.setAttribute("listaDepartamentos", lista);

        // ✅ 3. Redirigir a JSP
        request.getRequestDispatcher("/WEB-INF/jsp/crearUsuario.jsp")
                .forward(request, response);
    }
}
