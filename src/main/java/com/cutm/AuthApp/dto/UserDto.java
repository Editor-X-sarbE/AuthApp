package com.cutm.AuthApp.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.cutm.AuthApp.entity.Provider;
import com.cutm.AuthApp.entity.Role;

// import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private UUID id;

    private String email;

    private String name;
    private String password;
    private String image;
    private boolean enable = true;

    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();

    private Provider provider = Provider.Local;

    private Set<RoleDto> roles = new HashSet<>();
}