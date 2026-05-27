package com.cutm.AuthApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cutm.AuthApp.dto.UserDto;
import com.cutm.AuthApp.entity.Provider;
import com.cutm.AuthApp.entity.User;
import com.cutm.AuthApp.repositories.UserRepository;
import com.cutm.AuthApp.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // this autometically creates a parameterized constructor
public class UserServiceImplementation implements UserService {

    // constructor injection
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        // checking the user email part is fill or blank
        if (userDto.getEmail() == null || userDto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email not found .");
        }

        // checking user already exit or not
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email already Exist .");
        }

        User user = modelMapper.map(userDto, User.class);
        user.setProvider(userDto.getProvider() != null ? userDto.getProvider() : Provider.Local);
        // role assing here to User... for authorization
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
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
