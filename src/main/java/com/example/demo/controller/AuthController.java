package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Guest;
import com.example.demo.repository.GuestRepository;

@RestController
public class AuthController {

    @Autowired
    private GuestRepository guestRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Guest guest) {

        // check email already exists
        if (guestRepository.existsByEmail(guest.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        guestRepository.save(guest);
        return ResponseEntity.ok("Guest registered successfully");
    }
}
