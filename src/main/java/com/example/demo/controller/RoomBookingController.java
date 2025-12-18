package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.RoomBooking;
import com.example.demo.service.RoomBookingService;

@RestController
public class RoomBookingController {

    private final RoomBookingService roomBookingService;

    public RoomBookingController(RoomBookingService roomBookingService) {
        this.roomBookingService = roomBookingService;
    }

    @PostMapping("/addbooking")
    public RoomBooking addBooking(@RequestBody RoomBooking booking) {
        return roomBookingService.createBooking(booking);
    }

    @GetMapping("/showbookings")
    public List<RoomBooking> showBookings() {
        return roomBookingService.getAllBookings();
    }
}
