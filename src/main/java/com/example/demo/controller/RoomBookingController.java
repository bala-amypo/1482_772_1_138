package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.RoomBookingEntity;
import com.example.demo.service.RoomBookingService;

@RestController
@RequestMapping("/booking")
public class RoomBookingController {

    private final RoomBookingService roomBookingService;

    public RoomBookingController(RoomBookingService roomBookingService) {
        this.roomBookingService = roomBookingService;
    }

    // CREATE BOOKING
    @PostMapping("/add")
    public RoomBookingEntity createBooking(@RequestBody RoomBookingEntity booking) {
        return roomBookingService.createBooking(booking);
    }

    // GET ALL BOOKINGS
    @GetMapping("/all")
    public List<RoomBookingEntity> getAllBookings() {
        return roomBookingService.getAllBookings();
    }

    // GET BOOKING BY ID
    @GetMapping("/{id}")
    public RoomBookingEntity getBookingById(@PathVariable Long id) {
        return roomBookingService.getBookingById(id);
    }
}
