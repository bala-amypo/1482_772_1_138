package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.RoomBooking;
import com.example.demo.repository.RoomBookingRepository;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;

    public RoomBookingServiceImpl(RoomBookingRepository roomBookingRepository) {
        this.roomBookingRepository = roomBookingRepository;
    }

    @Override
    public RoomBooking createBooking(RoomBooking booking) {

        if (booking.getCheckIn().isAfter(booking.getCheckOut())) {
            throw new IllegalArgumentException("check-in must be before check-out");
        }

        return roomBookingRepository.save(booking);
    }

    @Override
    public List<RoomBooking> getAllBookings() {
        return roomBookingRepository.findAll();
    }
}
