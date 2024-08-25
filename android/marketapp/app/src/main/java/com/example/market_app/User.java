package com.example.market_app;

public class User {
    private String email;
    private String password;
    private String uuid;


    public User() {
        this("", "", "");
    }

    public User(String uuid, String email, String password) {
        this.email = email;
        this.password = password;
        this.uuid = uuid;
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
