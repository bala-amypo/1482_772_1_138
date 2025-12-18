package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.GuestEntity;
import com.example.demo.service.GuestService;

@RestController
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping("/addguest")
    public GuestEntity addGuest(@RequestBody GuestEntity guest) {
        return guestService.createGuest(guest);
    }

    @GetMapping("/showguests")
    public List<GuestEntity> showGuests() {
        return guestService.getAllGuests();
    }
}



