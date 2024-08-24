package com.example.app.model;

import java.util.UUID;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {
    @Id
    private String uuid;

    @Column(unique = true)
    private String email;

    private String password;

    public User() {
        this("", "");
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}