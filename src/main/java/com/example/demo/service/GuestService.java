package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Guest;

public interface GuestService {

    Guest createGuest(Guest guest);

    List<Guest> getAllGuests();

    Guest getGuestByEmail(String email);

    Guest getGuestById(Long id);

    // 🔥 REQUIRED METHOD
    void deactivateGuest(Long id);
}
