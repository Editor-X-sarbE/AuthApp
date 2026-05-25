package com.cutm.AuthApp.entity;

import java.time.Instant;
import java.util.UUID;

public class User {
    private UUID id;

    private String name;
    private String email;
    private String password;
    private String image;
    private boolean enable = true;

    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
}
