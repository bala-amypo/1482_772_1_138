package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Guest;

public interface GuestService {

    Guest createGuest(Guest guest);

    Guest updateGuest(Long id, Guest guest);   // 🔥 ADD THIS

    List<Guest> getAllGuests();

    Guest getGuestById(Long id);

    Guest getGuestByEmail(String email);

    void deactivateGuest(Long id);
}
