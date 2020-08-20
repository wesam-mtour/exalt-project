package com.exalt.sparepartsmanagement.security.configurations;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomGatewayFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String port = request.getHeader("x-forwarded-port");

        if (port == null || !port.equals("8082")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);

        } else {
            chain.doFilter(request, response);
        }
    }
}