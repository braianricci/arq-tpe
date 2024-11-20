package com.example.usuario_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import com.example.usuario_service.model.entity.Usuario.Rol;

public class JwtUtil {
    private static final String SECRET_KEY = "miClaveSecreta";

    public static String generateToken(String email, Rol role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

}