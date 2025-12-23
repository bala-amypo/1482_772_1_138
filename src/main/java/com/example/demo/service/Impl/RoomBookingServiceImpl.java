package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.RoomBooking;
import com.example.demo.repository.RoomBookingRepository;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository bookingRepo;

    public RoomBookingServiceImpl(RoomBookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    @Override
    public RoomBooking createBooking(RoomBooking booking) {

        if (!booking.getCheckInDate().isBefore(booking.getCheckOutDate())) {
            throw new IllegalArgumentException("Check-in must be before check-out");
        }

        return bookingRepo.save(booking);
    }

    @Override
    public RoomBooking getBookingById(Long id) {
        return bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public List<RoomBooking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    public List<RoomBooking> getBookingsByGuestId(Long guestId) {
        return bookingRepo.findByGuestId(guestId);
    }

    @Override
    public RoomBooking updateBooking(Long id, RoomBooking booking) {

        RoomBooking existing = getBookingById(id);
        existing.setRoomNumber(booking.getRoomNumber());
        existing.setCheckInDate(booking.getCheckInDate());
        existing.setCheckOutDate(booking.getCheckOutDate());
        existing.setRoommates(booking.getRoommates());

        return bookingRepo.save(existing);
    }

    @Override
    public void cancelBooking(Long id) {
        RoomBooking booking = getBookingById(id);
        booking.setActive(false);
        bookingRepo.save(booking);
    }
}
