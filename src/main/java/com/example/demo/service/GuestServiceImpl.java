package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.model.GuestEntity;
import com.example.demo.repository.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public GuestEntity createGuest(GuestEntity guest) {
        return guestRepository.save(guest);
    }

    @Override
    public List<GuestEntity> getAllGuests() {
        return guestRepository.findAll();
    }
}
