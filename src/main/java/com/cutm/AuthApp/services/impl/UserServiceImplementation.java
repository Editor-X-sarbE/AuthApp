package com.cutm.AuthApp.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cutm.AuthApp.dto.UserDto;
import com.cutm.AuthApp.entity.Provider;
import com.cutm.AuthApp.entity.User;
import com.cutm.AuthApp.exceptions.ResourceNotFoundException;
import com.cutm.AuthApp.repositories.UserRepository;
import com.cutm.AuthApp.services.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // this autometically creates a parameterized constructor
public class UserServiceImplementation implements UserService {

    // constructor injection
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
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
        // model mapper used for Converting entites in to DTO
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with given email id"));
        return modelMapper.map(user, UserDto.class);
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
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

    // private static class ResourceNotFoundException extends RuntimeException {
    // public ResourceNotFoundException(String message) {
    // super(message);
    // }
    // }

}
