package com.example.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.app.context.UserRepository;

import com.example.app.model.User;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> createUser(User entity) {
        try {
            User user = new User();
            user.setEmail(entity.getEmail());
            user.setPassword(entity.getPassword());
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

	public ResponseEntity<User> getUser(String uuid) {
        try {
            Optional<User> optional = userRepository.findByUuid(uuid);
            if (optional.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
	}

	public ResponseEntity<User> login(User entity) {
        try {
            Optional<User> optional = userRepository.findByEmail(entity.getEmail());
            if (optional.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            User user = optional.get();
            if (user.getPassword() != entity.getPassword())
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
	}

	public ResponseEntity<User> deleteUser(String uuid) {
        try {
            Optional<User> optional = userRepository.findByUuid(uuid);
            if (optional.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            userRepository.delete(optional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
	}

	public ResponseEntity<User> updateUser(String uuid, User entity) {
        try {
            Optional<User> optional = userRepository.findByUuid(uuid);
            if (optional.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            User user = optional.get();
            user.setEmail(entity.getEmail());
            user.setPassword(entity.getPassword());
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
	}

}