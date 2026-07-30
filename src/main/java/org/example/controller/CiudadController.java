package org.example.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Ciudad;
import org.example.repository.CiudadRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ciudadesPorDepartamento")
public class CiudadController extends HttpServlet {

    private CiudadRepository ciudadRepository = new CiudadRepository();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        String depParam = request.getParameter("departamentoId");

        if (depParam == null || depParam.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int departamentoId = Integer.parseInt(depParam);

        List<Ciudad> lista = ciudadRepository.listarPorDepartamento(departamentoId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.print("[");
        for (int i = 0; i < lista.size(); i++) {

            Ciudad c = lista.get(i);

            out.print("{\"id\":" + c.getId() +
                    ",\"nombre\":\"" + c.getNombre() + "\"}");

            if (i < lista.size() - 1) {
                out.print(",");
            }
        }
        out.print("]");
    }
}
