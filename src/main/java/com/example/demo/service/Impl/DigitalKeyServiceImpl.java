package com.example.demo.service.impl;

import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.service.DigitalKeyService;
import com.example.demo.model.DigitalKey;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DigitalKeyServiceImpl implements DigitalKeyService {

    private final DigitalKeyRepository repo;

    public DigitalKeyServiceImpl(DigitalKeyRepository repo) {
        this.repo = repo;
    }

    @Override
    public DigitalKey getActiveKeyForBooking(Long bookingId) {
        return repo.findByBookingIdAndActiveTrue(bookingId);
    }

    @Override
    public List<DigitalKey> getKeysForGuest(Long guestId) {
        return repo.findByBookingGuestId(guestId);
    }
}
