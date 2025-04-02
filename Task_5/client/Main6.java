package com.techshop.client;

import com.techshop.exception.AuthenticationException;
import com.techshop.exception.AuthorizationException;
import com.techshop.security.SecurityManager;
import com.techshop.security.User;

public class Main6 {
    public static void main(String[] args) {
        try {

            User user1 = User.authenticate("alice", "password123");
            System.out.println("User " + user1.getUsername() + " logged in successfully.");

            try {
                SecurityManager.checkAdminAccess(user1);
                System.out.println("Admin access granted.");
            } catch (AuthorizationException e) {
                System.err.println(e.getMessage());
            }
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
        }

        try {

            User admin = User.authenticate("admin", "adminpass");
            System.out.println("\n Admin " + admin.getUsername() + " logged in successfully.");

            try {
                SecurityManager.checkAdminAccess(admin);
                System.out.println("Admin access granted.");
            } catch (AuthorizationException e) {
                System.err.println(e.getMessage());
            }
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
        }
    }
}