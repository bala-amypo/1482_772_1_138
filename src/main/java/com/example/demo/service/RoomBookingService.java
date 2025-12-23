package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.RoomBooking;

public interface RoomBookingService {

    RoomBooking createBooking(RoomBooking booking);

    RoomBooking getBookingById(Long id);

    List<RoomBooking> getAllBookings();

    List<RoomBooking> getBookingsByGuestId(Long guestId);

    RoomBooking updateBooking(Long id, RoomBooking booking);

    void cancelBooking(Long id);
}
