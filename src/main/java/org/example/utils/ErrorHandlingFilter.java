package org.example.utils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.logging.Logger;

@WebFilter("/*")
public class ErrorHandlingFilter implements Filter {

    private static final Logger logger =
            Logger.getLogger(ErrorHandlingFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {

            logger.severe("Error global: " + e.getMessage());

            request.setAttribute("error", "Error interno del sistema");

            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp")
                    .forward(request, response);
        }
    }
}