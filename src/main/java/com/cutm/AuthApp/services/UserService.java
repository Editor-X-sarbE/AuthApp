package com.cutm.AuthApp.services;

import com.cutm.AuthApp.dto.UserDto;

public interface UserService {

    // create user Controller
    UserDto createUser(UserDto userDto);

    // access through email Controller
    UserDto getUserByEmail(String email);

    // update user by email Controller
    UserDto updateUser(UserDto userDto, String userId);

    // delete user Controller
    void deleteUser(String userID);

    // access through Id Controller
    UserDto getuserById(String userID);

    // accessing all users Controller
    Iterable<UserDto> getAllUsers();
}
