package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Guest;

public interface GuestService {

    Guest createGuest(Guest guest);

    Guest getGuestById(Long id);

    List<Guest> getAllGuests();

    Guest updateGuest(Long id, Guest guest);

    void deactivateGuest(Long id);
}
