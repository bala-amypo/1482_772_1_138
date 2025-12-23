package com.example.demo.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.entity.DigitalKey;
import com.example.demo.entity.RoomBooking;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.RoomBookingRepository;

@Service
public class DigitalKeyServiceImpl implements DigitalKeyService {

    private final DigitalKeyRepository keyRepo;
    private final RoomBookingRepository bookingRepo;

    public DigitalKeyServiceImpl(DigitalKeyRepository keyRepo,
                                 RoomBookingRepository bookingRepo) {
        this.keyRepo = keyRepo;
        this.bookingRepo = bookingRepo;
    }

    @Override
    public DigitalKey generateKey(Long bookingId) {

        RoomBooking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getActive()) {
            throw new IllegalStateException("inactive booking");
        }

        DigitalKey key = new DigitalKey();
        key.setBooking(booking);
        key.setKeyValue(UUID.randomUUID().toString());
        key.setIssuedAt(new Timestamp(System.currentTimeMillis()));
        key.setActive(true);

        return keyRepo.save(key);
    }

    @Override
    public DigitalKey getKeyById(Long id) {
        return keyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Key not found"));
    }

    @Override
    public List<DigitalKey> getKeysByGuestId(Long guestId) {
        return keyRepo.findByBookingGuestId(guestId);
    }

    @Override
    public void deactivateKey(Long id) {
        DigitalKey key = getKeyById(id);
        key.setActive(false);
        keyRepo.save(key);
    }
}
