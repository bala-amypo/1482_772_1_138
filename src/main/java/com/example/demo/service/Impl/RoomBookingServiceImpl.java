package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.RoomBooking;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.RoomBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ✅ THIS WAS MISSING (LAST ONE)
public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository repo;

    public RoomBookingServiceImpl(RoomBookingRepository repo) {
        this.repo = repo;
    }

    @Override
    public RoomBooking createBooking(RoomBooking booking) {

        if (booking.getCheckInDate()
                .isAfter(booking.getCheckOutDate())) {
            throw new IllegalArgumentException("Check-in date must be before check-out date");
        }

        booking.setActive(true);
        return repo.save(booking);
    }

    @Override
    public RoomBooking getBookingById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found " + id));
    }

    @Override
    public List<RoomBooking> getBookingsForGuest(Long guestId) {
        return repo.findByGuestId(guestId);
    }

    @Override
    public RoomBooking updateBooking(Long id, RoomBooking booking) {

        RoomBooking existing = getBookingById(id);

        existing.setRoomNumber(booking.getRoomNumber());
        existing.setCheckInDate(booking.getCheckInDate());
        existing.setCheckOutDate(booking.getCheckOutDate());

        return repo.save(existing);
    }

    @Override
    public void deactivateBooking(Long id) {
        RoomBooking booking = getBookingById(id);
        booking.setActive(false);
        repo.save(booking);
    }
}
