package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DigitalKeyService;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class DigitalKeyServiceImpl implements DigitalKeyService {

    private final DigitalKeyRepository repo;
    private final RoomBookingRepository bookingRepo;

    public DigitalKeyServiceImpl(DigitalKeyRepository repo, RoomBookingRepository bookingRepo) {
        this.repo = repo;
        this.bookingRepo = bookingRepo;
    }

    public DigitalKey generateKey(Long bookingId) {
        RoomBooking b = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking " + bookingId));

        if (!b.getActive())
            throw new IllegalStateException("inactive booking");

        DigitalKey k = new DigitalKey();
        k.setBooking(b);
        k.setKeyValue(UUID.randomUUID().toString());
        k.setIssuedAt(Instant.now());
        k.setExpiresAt(Instant.now().plusSeconds(3600));
        return repo.save(k);
    }

    public DigitalKey getActiveKeyForBooking(Long id) {
        return repo.findByBookingIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key " + id));
    }

    public List<DigitalKey> getKeysForGuest(Long guestId) {
        return repo.findByBookingGuestId(guestId);
    }
}
