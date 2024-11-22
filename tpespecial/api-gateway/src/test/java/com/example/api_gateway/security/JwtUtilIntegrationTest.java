package com.example.api_gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.nio.charset.StandardCharsets;
import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class JwtUtilIntegrationTest {

    @Autowired
    private JwtUtil jwtUtil;

    private String validToken;

    @BeforeEach
    void setUp() {
        // Crear un token de prueba válido
        Key key = Keys.hmacShaKeyFor("miClaveSecretaMuyLargaYSeguraParaGenerarTokensJWT2024!@#$%^&*()".getBytes(StandardCharsets.UTF_8));
        validToken = Jwts.builder()
                .setSubject("juan@gmail.com")
                .claim("role", "ADMIN")
                .signWith(key)
                .compact();
    }

    @Test
    void validateToken_ValidToken_ShouldReturnClaims() {
        // Act
        Claims claims = jwtUtil.validateToken(validToken);

        // Assert
        assertNotNull(claims);
        assertEquals("juan@gmail.com", claims.getSubject());
        assertEquals("ADMIN", claims.get("role"));
    }

    @Test
    void validateToken_InvalidToken_ShouldThrowException() {
        // Arrange
        String invalidToken = "invalid.token.here";

        // Act & Assert
        assertThrows(io.jsonwebtoken.JwtException.class, () -> {
            jwtUtil.validateToken(invalidToken);
        });
    }
}
