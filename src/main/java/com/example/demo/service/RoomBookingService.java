package com.example.demo.service;

import com.example.demo.model.RoomBooking;
import java.util.List;

public interface RoomBookingService {
    List<RoomBooking> getBookingsForGuest(Long guestId);
}
