package com.example.app.context;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.User;

public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByUuid(String uuid);

    Optional<User> findByEmail(String email);
}