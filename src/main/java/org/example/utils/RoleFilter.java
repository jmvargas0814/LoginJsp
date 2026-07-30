package org.example.utils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.Usuario;

import java.io.IOException;

@WebFilter("/crearUsuario")
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session != null) {

            Usuario usuario =
                    (Usuario) session.getAttribute("usuario");

            if (usuario != null &&
                    !"ADMIN".equals(usuario.getRol())) {

                res.sendRedirect(req.getContextPath() + "/home");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
