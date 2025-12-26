package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.Guest;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.security.GuestPrincipal;
import com.example.demo.service.GuestService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final GuestService guestService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            GuestService guestService,
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider) {
        this.guestService = guestService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public TokenResponse register(@RequestBody RegisterRequest request) {

        Guest g = new Guest();
        g.setFullName(request.getFullName());
        g.setEmail(request.getEmail());
        g.setPhoneNumber(request.getPhoneNumber());
        g.setPassword(request.getPassword());
        g.setRole(request.getRole() != null ? request.getRole() : "ROLE_USER");

        Guest saved = guestService.createGuest(g);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                saved.getEmail(),
                request.getPassword()
        );

        String token = jwtTokenProvider.generateToken(auth);

        return new TokenResponse(token, saved.getId(), saved.getEmail(), saved.getRole());
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        GuestPrincipal principal = (GuestPrincipal) auth.getPrincipal();
        String token = jwtTokenProvider.generateToken(auth);

        return new TokenResponse(
                token,
                principal.getId(),
                principal.getUsername(),
                principal.getRole()
        );
    }
}
