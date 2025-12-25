package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepo;

    @Override
    public Guest createGuest(Guest guest) {
        if (guestRepo.existsByEmail(guest.getEmail())) {
            throw new RuntimeException("Guest already exists with email: " + guest.getEmail());
        }
        guest.setActive(true);
        return guestRepo.save(guest);
    }

    // 🔥 THIS FIXES YOUR CURRENT ERROR
    @Override
    public Guest updateGuest(Long id, Guest guest) {
        Guest existing = guestRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));

        existing.setFullName(guest.getFullName());
        existing.setEmail(guest.getEmail());
        existing.setPhoneNumber(guest.getPhoneNumber());
        existing.setVerified(guest.isVerified());
        existing.setRole(guest.getRole());

        return guestRepo.save(existing);
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestRepo.findAll();
    }

    @Override
    public Guest getGuestById(Long id) {
        return guestRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));
    }

    @Override
    public Guest getGuestByEmail(String email) {
        return guestRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Guest not found with email: " + email));
    }

    @Override
    public void deactivateGuest(Long id) {
        Guest guest = guestRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));

        guest.setActive(false);
        guestRepo.save(guest);
    }
}
