package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.app.model.User;
import com.example.app.services.UserServices;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
class UserController {
    @Autowired
    private UserServices userServices;


    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User entity) {
        return userServices.createUser(entity);
    }

    @GetMapping("/info/{uuid}")
    public ResponseEntity<User> getUser(@PathVariable String uuid) {
        return userServices.getUser(uuid);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User entity) {
        return userServices.login(entity);
    }

    @PatchMapping("/update/{uuid}")
    public ResponseEntity<User> updateUser(@PathVariable String uuid, @RequestBody User entity) {
        return userServices.updateUser(uuid, entity);
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<User> deleteUser(@PathVariable String uuid) {
        return userServices.deleteUser(uuid);
    }

}