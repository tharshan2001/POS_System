package com.pos.system.security;

import com.pos.system.entity.people.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FindCurrentUser {

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User user) {
            return user; // Return the User entity directly
        }

        throw new RuntimeException("No authenticated user");
    }
}