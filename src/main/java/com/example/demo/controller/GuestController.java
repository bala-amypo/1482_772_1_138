package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Guest;
import com.example.demo.service.GuestService;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public Guest createGuest(@RequestBody Guest guest) {
        return guestService.createGuest(guest);
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable Long id) {
        return guestService.getGuestById(id);
    }

    @GetMapping
    public List<Guest> getAllGuests() {
        return guestService.getAllGuests();
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable Long id, @RequestBody Guest guest) {
        return guestService.updateGuest(id, guest);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateGuest(@PathVariable Long id) {
        guestService.deactivateGuest(id);
    }
}
