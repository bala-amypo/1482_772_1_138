package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Guest;
import com.example.demo.repository.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepo;

    public GuestServiceImpl(GuestRepository guestRepo) {
        this.guestRepo = guestRepo;
    }

    @Override
    public Guest createGuest(Guest guest) {
        if (guestRepo.findByEmail(guest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        return guestRepo.save(guest);
    }

    @Override
    public Guest getGuestById(Long id) {
        return guestRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found"));
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestRepo.findAll();
    }

    @Override
    public Guest updateGuest(Long id, Guest guest) {
        Guest existing = getGuestById(id);
        existing.setFullName(guest.getFullName());
        existing.setPhoneNumber(guest.getPhoneNumber());
        existing.setVerified(guest.getVerified());
        existing.setRole(guest.getRole());
        return guestRepo.save(existing);
    }

    @Override
    public void deactivateGuest(Long id) {
        Guest guest = getGuestById(id);
        guest.setActive(false);
        guestRepo.save(guest);
    }
}
