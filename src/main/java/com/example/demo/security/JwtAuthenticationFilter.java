package com.example.demo.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class JwtAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(request, response);
    }
}
