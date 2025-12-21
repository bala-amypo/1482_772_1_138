package com.example.demo.service;

import java.util.List;
import com.example.demo.model.GuestEntity;

public interface GuestService {
    GuestEntity createGuest(GuestEntity guest);
    List<GuestEntity> getAllGuests();
}
