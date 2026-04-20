package com.example.demo.model;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "users")
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID userId;

    private String username;
    private String email;
    private String password;
    Boolean isActive;

}
