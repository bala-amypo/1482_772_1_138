package com.example.demo.service;

import java.util.List;

import com.example.demo.model.GuestEntity;

public interface GuestService {

    GuestEntity addGuest(GuestEntity guest);

    List<GuestEntity> getAllGuests();

    GuestEntity getGuestById(Long id);
}
