package com.example.api_gateway.security;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Claims;
import java.io.IOException;

public class JwtAuthenticationFilterTest {

    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        // Crear un mock de JwtUtil
        jwtUtil = mock(JwtUtil.class);
        // Crear el filtro con el mock
        jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtil);
    }

    @Test
    public void testDoFilterInternal() throws ServletException, IOException {
        // Configura los objetos mock (HttpServletRequest, HttpServletResponse, FilterChain)
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        // Simula el token JWT en el request (por ejemplo, con un header Authorization)
        when(request.getHeader("Authorization")).thenReturn("Bearer mock.jwt.token");

        // Crear un mock de Claims que se devolverá al validar el token
        Claims mockClaims = mock(Claims.class);
        when(jwtUtil.validateToken("mock.jwt.token")).thenReturn(mockClaims);

        // Llama al método doFilterInternal
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Verifica que el filtro continúa con el siguiente filtro en la cadena
        verify(filterChain).doFilter(request, response);
    }
}

