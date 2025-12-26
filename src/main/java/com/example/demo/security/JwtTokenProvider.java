package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component   // ✅ ADD THIS
public class JwtTokenProvider {

    private final String secret = "test-secret-key";
    private final long validityMs = 3600000;

    public String generateToken(Authentication authentication) {

        GuestPrincipal principal =
                (GuestPrincipal) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .claim("userId", principal.getId())
                .claim("role", principal.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("userId", Long.class);
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}
