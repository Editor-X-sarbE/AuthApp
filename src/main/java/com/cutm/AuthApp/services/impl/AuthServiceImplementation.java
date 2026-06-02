package com.cutm.AuthApp.services.impl;

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

    @Override
    public UserDto registerUser(UserDto userDto) {
        // logic
        // verify email
        // verify password
        // default roles
        UserDto userDto1 = userService.createUser(userDto);
        return userDto1;
    }
}
