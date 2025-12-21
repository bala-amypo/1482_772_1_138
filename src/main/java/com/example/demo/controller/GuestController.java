package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.GuestEntity;
import com.example.demo.service.GuestService;

@RestController
@RequestMapping("/guest")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    // ADD GUEST
    @PostMapping("/add")
    public GuestEntity addGuest(@RequestBody GuestEntity guest) {
        return guestService.addGuest(guest);
    }

    // GET ALL GUESTS
    @GetMapping("/all")
    public List<GuestEntity> getAllGuests() {
        return guestService.getAllGuests();
    }

    // GET GUEST BY ID
    @GetMapping("/{id}")
    public GuestEntity getGuestById(@PathVariable Long id) {
        return guestService.getGuestById(id);
    }
}
