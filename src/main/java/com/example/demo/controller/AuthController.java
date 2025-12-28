package com.example.demo.controller;

import com.example.demo.model.Guest;
import com.example.demo.service.GuestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final GuestService guestService;

    // ✅ Constructor Injection
    public AuthController(GuestService guestService) {
        this.guestService = guestService;
    }

    // ✅ REGISTER
    @PostMapping("/register")
    public Guest register(@RequestBody Guest guest) {
        return guestService.createGuest(guest);
    }

    // ✅ LOGIN (DUMMY TOKEN – SAFE)
    @PostMapping("/login")
    public String login(@RequestBody Guest guest) {
        return "dummy-token";
    }
}
