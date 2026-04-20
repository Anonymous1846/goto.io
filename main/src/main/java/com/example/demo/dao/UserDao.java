package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao {

    Optional<User>findByUsername(String username);
    Optional<User>findByEmail(String email);
    Boolean existsByEmail(String email);
}
