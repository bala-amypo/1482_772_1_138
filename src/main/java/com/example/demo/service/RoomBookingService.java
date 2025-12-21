package com.example.demo.service;

import java.util.List;

import com.example.demo.model.RoomBookingEntity;

public interface RoomBookingService {

    RoomBookingEntity createBooking(RoomBookingEntity booking);

    List<RoomBookingEntity> getAllBookings();

    RoomBookingEntity getBookingById(Long id);
}
