package com.movie.demo.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil
{
    private static final String SECRET =
            "hind@1234567890_size is120soneedtoincreaseittosomextentsoerrordoesntoccur";

    private static final long EXPIRATION_TIME =
            1000 * 60 * 60 * 10; // 10 hours

    private final SecretKey secretKey =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    //private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // CREATE TOKEN
    public String generateToken(String email,String role) {
        return Jwts.builder()
                .setClaims(Map.of("role", role))
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
    public String extractRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role",String.class);
    }

    // VALIDATE TOKEN
    public boolean isTokenValid(String token, String email) {
        try {
            Date expiration = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();

            return email.equals(extractEmail(token))
                    && expiration.after(new Date());

        } catch (Exception e) {
            return false;
        }
    }

}
