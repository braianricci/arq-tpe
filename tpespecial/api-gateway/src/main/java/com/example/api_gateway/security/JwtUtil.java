package com.example.api_gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "miClaveSecretaMuyLargaYSeguraParaGenerarTokensJWT2024!@#$%^&*()";

    public Claims validateToken(String token) {
        // Use the secret key directly to generate the Key object
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        // Validate the token using the secret key
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}