package com.techshop.security;

import com.techshop.exception.AuthenticationException;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String password;
    private String role; // Roles: "USER", "ADMIN"

    private static Map<String, User> userDatabase = new HashMap<>();

    static {
        // Simulating stored users
        userDatabase.put("alice", new User("alice", "password123", "USER"));
        userDatabase.put("admin", new User("admin", "adminpass", "ADMIN"));
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getRole() { return role; }

    public static User authenticate(String username, String password) {
        User user = userDatabase.get(username);
        if (user == null || !user.password.equals(password)) {
            throw new AuthenticationException("Invalid username or password.");
        }
        return user;
    }
}