package com.exalt.sparepartsmanagement.security.model;

import java.util.*;

import com.exalt.sparepartsmanagement.model.Employee;
import com.exalt.sparepartsmanagement.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {


    UserDetailsServiceImpl ff ;


    private Employee user;

    public MyUserDetails(Employee user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        ff = UserDetailsServiceImpl.inst;
        roles.addAll( ff.getRoles());

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            System.out.println("\n" + "**************************************************");
            System.out.println(role);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getName();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}