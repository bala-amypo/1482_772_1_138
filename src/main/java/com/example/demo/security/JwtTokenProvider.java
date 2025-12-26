package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    // MUST be >= 256 bits for HS256
    private final SecretKey secretKey =
            Keys.hmacShaKeyFor("my-super-secret-key-for-jwt-token-generation-123456".getBytes());

    private final long validityInMillis = 3600000; // 1 hour

    // ================== GENERATE TOKEN ==================
    public String generateToken(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String email = userDetails.getUsername();

        // Extract ROLE
        String role = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");

        // USER ID MUST COME FROM CustomUserDetails
        Long userId = null;
        if (userDetails instanceof CustomUserDetails cud) {
            userId = cud.getId();
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("role", role);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // ================== VALIDATE TOKEN ==================
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    // ================== EXTRACT EMAIL ==================
    public String getEmailFromToken(String token) {
        return getAllClaims(token).get("email", String.class);
    }

    // ================== EXTRACT ROLE ==================
    public String getRoleFromToken(String token) {
        return getAllClaims(token).get("role", String.class);
    }

    // ================== EXTRACT USER ID ==================
    public Long getUserIdFromToken(String token) {
        return getAllClaims(token).get("userId", Long.class);
    }

    // ================== COMMON CLAIM PARSER ==================
    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
