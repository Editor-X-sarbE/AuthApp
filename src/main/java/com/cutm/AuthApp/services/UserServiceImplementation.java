package com.cutm.AuthApp.services;

import org.springframework.stereotype.Service;

import com.cutm.AuthApp.dto.UserDto;

@Service
public class UserServiceImplementation implements UserService {

    @Override
    public UserDto createUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        return null;
    }

    @Override
    public void deleteUser(String userID) {

    }

    @Override
    public UserDto getuserById(String userID) {
        return null;
    }

    @Override
    public Iterable<UserDto> getAllUsers() {
        return null;
    }

}
