package com.techshop.security;

import com.techshop.exception.AuthorizationException;

public class SecurityManager {
    public static void checkAdminAccess(User user) {
        if (!"ADMIN".equals(user.getRole())) {
            throw new AuthorizationException("Access Denied: Admin privileges required.");
        }
    }
}