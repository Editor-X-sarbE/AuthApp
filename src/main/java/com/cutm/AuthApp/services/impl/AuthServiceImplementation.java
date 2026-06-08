package com.cutm.AuthApp.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cutm.AuthApp.dto.UserDto;
// import com.cutm.AuthApp.repositories.UserRepository;
import com.cutm.AuthApp.services.AuthService;
import com.cutm.AuthApp.services.UserService;

import lombok.AllArgsConstructor;
// import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {
        // logic
        // verify email
        // verify password
        // default roles
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userService.createUser(userDto);
    }
}
