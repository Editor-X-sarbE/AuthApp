package com.cutm.AuthApp.services;

import com.cutm.AuthApp.dto.UserDto;

public interface AuthService {
    // register user
    UserDto registerUser(UserDto userDto);
    // login user
}
