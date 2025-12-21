package com.example.demo.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "room_booking")
public class RoomBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many bookings belong to one guest
    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private GuestEntity guest;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "check_in_date")
    private LocalDate checkInDate;

    @Column(name = "check_out_date")
    private LocalDate checkOutDate;

    @Column(name = "active")
    private Boolean active = true;

    // Many roommates for a booking
    @ManyToMany
    @JoinTable(
        name = "room_booking_roommates",
        joinColumns = @JoinColumn(name = "room_booking_id"),
        inverseJoinColumns = @JoinColumn(name = "guest_id")
    )
    private Set<GuestEntity> roommates = new HashSet<>();

    public RoomBooking() {}

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public GuestEntity getGuest() {
        return guest;
    }

    public void setGuest(GuestEntity guest) {
        this.guest = guest;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<GuestEntity> getRoommates() {
        return roommates;
    }

    public void setRoommates(Set<GuestEntity> roommates) {
        this.roommates = roommates;
    }
}
