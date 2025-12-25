package com.example.demo.security;

import com.example.demo.entity.Guest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class GuestPrincipal implements UserDetails {

    private final Guest guest;

    public GuestPrincipal(Guest guest) {
        this.guest = guest;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return "password";
    }

    @Override
    public String getUsername() {
        return guest.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return guest.getActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return guest.getActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return guest.getActive();
    }

    @Override
    public boolean isEnabled() {
        return guest.getActive();
    }
}
