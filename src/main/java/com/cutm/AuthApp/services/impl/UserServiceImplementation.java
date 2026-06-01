package com.cutm.AuthApp.services.impl;

import java.time.Instant;
import java.util.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cutm.AuthApp.dto.UserDto;
import com.cutm.AuthApp.entity.Provider;
import com.cutm.AuthApp.entity.User;
import com.cutm.AuthApp.exceptions.ResourceNotFoundException;
import com.cutm.AuthApp.helper.UserHelper;
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
        UUID uid = UserHelper.parseUUID(userId);
        User existingUser = userRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("User not founf with given ID ."));
        // we are not updating email id bc`s email is the primery
        if (userDto.getName() != null)
            existingUser.setName(userDto.getName());
        if (userDto.getImage() != null)
            existingUser.setImage(userDto.getImage());
        if (userDto.getProvider() != null)
            existingUser.setProvider(userDto.getProvider());
        // TODO Change password Updation Logic
        if (userDto.getPassword() != null)
            existingUser.setPassword(userDto.getPassword());
        existingUser.setEnable(userDto.isEnable());
        existingUser.setUpdatedAt(Instant.now());
        User updateUser = userRepository.save(existingUser);
        return modelMapper.map(updateUser, UserDto.class);
    }

    @Override
    public void deleteUser(String userID) {
        UUID uid = UserHelper.parseUUID(userID);
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given ID ."));
        userRepository.delete(user);
    }

    @Override
    public UserDto getuserById(String userID) {
        User user = userRepository.findById(UserHelper.parseUUID(userID))
                .orElseThrow(() -> new ResourceNotFoundException("User not foundwith given ID ."));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @Transactional
    public Iterable<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }

}
