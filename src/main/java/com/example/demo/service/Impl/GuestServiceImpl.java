package com.example.demo.service.impl;

import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository repository;
    private final PasswordEncoder passwordEncoder;

    public GuestServiceImpl(GuestRepository repository,
                            PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Guest createGuest(Guest guest) {
        if (repository.existsByEmail(guest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (guest.getPassword() != null) {
            guest.setPassword(passwordEncoder.encode(guest.getPassword()));
        }
        return repository.save(guest);
    }

    @Override
    public Guest updateGuest(Long id, Guest guest) {
        Guest existing = getGuestById(id);
        existing.setFullName(guest.getFullName());
        existing.setPhoneNumber(guest.getPhoneNumber());
        existing.setVerified(guest.getVerified());
        existing.setActive(guest.getActive());
        existing.setRole(guest.getRole());
        return repository.save(existing);
    }

    @Override
    public Guest getGuestById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));
    }

    @Override
    public List<Guest> getAllGuests() {
        return repository.findAll();
    }

    @Override
    public void deactivateGuest(Long id) {
        Guest guest = getGuestById(id);
        guest.setActive(false);
        repository.save(guest);
    }
}
