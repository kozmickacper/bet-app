package com.example.betapp.service;


import com.example.betapp.user_model.User;
import com.example.betapp.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);

    UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException;
}
