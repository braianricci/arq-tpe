package com.example.usuario_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import com.example.usuario_service.model.entity.Usuario.Rol;

public class JwtUtil {
    // Use a longer, more secure secret key
    private static final String SECRET_KEY = "miClaveSecretaMuyLargaYSeguraParaGenerarTokensJWT2024!@#$%^&*()";

    public static String generateToken(String email, Rol role) {
        // Convert the secret key to bytes using UTF-8 encoding
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role.toString()) // Convert Rol to string to ensure serialization
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}