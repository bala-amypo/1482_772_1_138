package com.example.demo.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Guest;

public class GuestPrincipal implements UserDetails {

    private final Guest guest;

    public GuestPrincipal(Guest guest) {
        this.guest = guest;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(
                new SimpleGrantedAuthority("ROLE_" + guest.getRole()));
    }

    @Override
    public String getPassword() {
        // No password in your model
        return null;
    }

    @Override
    public String getUsername() {
        return guest.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return guest.isActive();   // ✅ FIX
    }

    @Override
    public boolean isAccountNonLocked() {
        return guest.isActive();   // ✅ FIX
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return guest.isActive();   // ✅ FIX
    }

    @Override
    public boolean isEnabled() {
        return guest.isActive();   // ✅ FIX
    }
}
