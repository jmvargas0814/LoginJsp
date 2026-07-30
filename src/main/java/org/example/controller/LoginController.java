package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.UsuarioDTO;
import org.example.service.AuthService;

import java.io.IOException;
import java.util.Objects;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsuarioDTO usuario = authService.autenticar(username, password);

        if (Objects.isNull(usuario)) {
            request.setAttribute("error", "Credenciales incorrectas");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
                    .forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("usuario", usuario);

        // ✅ Forzar cambio de contraseña
        if (usuario.isCambiarPassword()) {
            response.sendRedirect(request.getContextPath() + "/cambiarPassword");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/home");
    }

}