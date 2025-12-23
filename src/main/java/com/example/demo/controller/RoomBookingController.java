package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.RoomBooking;
import com.example.demo.service.RoomBookingService;

@RestController
@RequestMapping("/api/bookings")
public class RoomBookingController {

    private final RoomBookingService bookingService;

    public RoomBookingController(RoomBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public RoomBooking createBooking(@RequestBody RoomBooking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("/{id}")
    public RoomBooking getBooking(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping
    public List<RoomBooking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/guest/{guestId}")
    public List<RoomBooking> getBookingsByGuest(@PathVariable Long guestId) {
        return bookingService.getBookingsByGuestId(guestId);
    }

    @PutMapping("/{id}")
    public RoomBooking updateBooking(@PathVariable Long id,
                                     @RequestBody RoomBooking booking) {
        return bookingService.updateBooking(id, booking);
    }

    @PutMapping("/{id}/cancel")
    public void cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
    }
}
