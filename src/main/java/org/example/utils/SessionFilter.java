package org.example.utils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebFilter("/*")
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();
        String contextPath = req.getContextPath();

        boolean isLogin = path.equals(contextPath + "/login");
        boolean isCambiarPassword = path.equals(contextPath + "/cambiarPassword");
        boolean isStatic = path.contains(".css")
                || path.contains(".js")
                || path.contains(".png")
                || path.contains(".jpg");

        HttpSession session = req.getSession(false);

        if (Objects.isNull(session)
                || Objects.isNull(session.getAttribute("usuario"))) {

            if (!isLogin && !isStatic && !isCambiarPassword) {

                res.sendRedirect(contextPath + "/login");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
