package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.RoomBooking;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.RoomBookingService;

import java.time.LocalDate;
import java.util.List;

public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository repository;

    public RoomBookingServiceImpl(RoomBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoomBooking createBooking(RoomBooking booking) {

        if (booking.getCheckInDate() == null || booking.getCheckOutDate() == null) {
            throw new IllegalArgumentException("Check-in and check-out required");
        }

        if (!booking.getCheckInDate().isBefore(booking.getCheckOutDate())) {
            throw new IllegalArgumentException("Check-in date must be before check-out date");
        }

        return repository.save(booking);
    }

    @Override
    public RoomBooking updateBooking(Long id, RoomBooking booking) {

        RoomBooking existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found " + id));

        if (booking.getCheckInDate() != null && booking.getCheckOutDate() != null) {
            if (!booking.getCheckInDate().isBefore(booking.getCheckOutDate())) {
                throw new IllegalArgumentException("Check-in date must be before check-out date");
            }
            existing.setCheckInDate(booking.getCheckInDate());
            existing.setCheckOutDate(booking.getCheckOutDate());
        }

        return repository.save(existing);
    }

    @Override
    public RoomBooking getBookingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found " + id));
    }

    @Override
    public List<RoomBooking> getBookingsForGuest(Long guestId) {
        return repository.findByGuestId(guestId);
    }

    @Override
    public void deactivateBooking(Long id) {
        RoomBooking booking = getBookingById(id);
        booking.setActive(false);
        repository.save(booking);
    }
}
