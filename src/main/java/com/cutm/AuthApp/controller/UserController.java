package com.cutm.AuthApp.controller;

import com.cutm.AuthApp.dto.UserDto;
import com.cutm.AuthApp.services.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Create User API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        UserDto createdUser = userService.createUser(userDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }

    // Get All Users API
    @GetMapping
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get User By Email API
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {

        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
}