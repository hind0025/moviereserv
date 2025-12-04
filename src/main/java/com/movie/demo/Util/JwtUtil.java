package com.movie.demo.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
@Component
public class JwtUtil
{
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // CREATE TOKEN
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 10)))  // 10 hours
                .signWith(secretKey)
                .compact();
    }

    // EXTRACT EMAIL
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // VALIDATE TOKEN
    public boolean isTokenValid(String token, String email) {
        return email.equals(extractEmail(token));
    }
}
