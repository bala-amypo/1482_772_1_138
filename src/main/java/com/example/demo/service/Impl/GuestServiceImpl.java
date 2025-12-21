package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.GuestEntity;
import com.example.demo.repository.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public GuestEntity addGuest(GuestEntity guest) {

        // email must be unique
        guestRepository.findByEmail(guest.getEmail())
                .ifPresent(g -> {
                    throw new RuntimeException("Email already exists");
                });

        return guestRepository.save(guest);
    }

    @Override
    public List<GuestEntity> getAllGuests() {
        return guestRepository.findAll();
    }

    @Override
    public GuestEntity getGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Guest not found with id: " + id));
    }
}
