package com.cutm.AuthApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cutm.AuthApp.dto.UserDto;
import com.cutm.AuthApp.services.UserService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    // create user API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(userDto));
    }

    // get all user API
    @GetMapping
    public ResponseEntity<Iterable<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
