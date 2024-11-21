package com.example.api_gateway.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // Omitir validación de token para los endpoints públicos
        if (path.equals("/usuarios") || path.equals("/usuarios/login") || path.equals("/usuarios/add")) {
            return chain.filter(exchange);  // Continuar sin validar el token
        }

        String token = resolveToken(exchange.getRequest()); // Obtener el token desde los encabezados

        if (token != null) {
            try {
                jwtUtil.validateToken(token); // Validar el token
            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED); // Responder con 401 si el token es inválido
                return exchange.getResponse().setComplete();
            }
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED); // Responder con 401 si no hay token
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange); // Continuar con el flujo si el token es válido
    }

    private String resolveToken(ServerHttpRequest request) {
        // Extract Authorization header
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        
        // Check if Authorization header exists and starts with "Bearer "
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // Return the token part after "Bearer "
            return bearerToken.substring(7);
        }
        
        return null;
    }
}
