package com.example.demo.model;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "access_log")
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Guest who accessed
    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private GuestEntity guest;

    // Digital key used
    @ManyToOne
    @JoinColumn(name = "digital_key_id", nullable = false)
    private DigitalKey digitalKey;

    private String action;   // UNLOCK / LOCK / ACCESS_DENIED

    private Timestamp accessedAt;

    @PrePersist
    protected void onAccess() {
        this.accessedAt = new Timestamp(System.currentTimeMillis());
    }

    public AccessLog() {}

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public GuestEntity getGuest() { return guest; }
    public void setGuest(GuestEntity guest) {
        this.guest = guest;
    }

    public DigitalKey getDigitalKey() { return digitalKey; }
    public void setDigitalKey(DigitalKey digitalKey) {
        this.digitalKey = digitalKey;
    }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public Timestamp getAccessedAt() { return accessedAt; }
}
