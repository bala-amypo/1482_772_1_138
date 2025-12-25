package com.example.demo.config;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public boolean validateToken(String token) {
        // Dummy validation for now
        return token != null && !token.isEmpty();
    }

    public String getUsernameFromToken(String token) {
        // Dummy extraction (replace with real JWT logic later)
        return token;
    }
}
