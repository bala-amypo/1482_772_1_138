package com.example.demo.service.impl;

import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.RoomBookingService;
import com.example.demo.model.RoomBooking;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository repo;

    public RoomBookingServiceImpl(RoomBookingRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<RoomBooking> getBookingsForGuest(Long guestId) {
        return repo.findByGuestId(guestId);
    }
}
