package com.pos.system.security;

import com.pos.system.entity.people.User;
import com.pos.system.repository.people.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Component("branchSecurity")
public class BranchSecurity {

    private final UserRepository userRepository;

    public BranchSecurity(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean canAccessBranch(Long branchId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        User user;
        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            user = (User) principal;
        } else {
            String username = authentication.getName();
            user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        String role = user.getRole() != null ? user.getRole().getName() : "";

        if ("SUPER_ADMIN".equalsIgnoreCase(role) || "ADMIN".equalsIgnoreCase(role)) {
            return true;
        }

        if (user.getBranch() == null) {
            return false;
        }

        return user.getBranch().getId().equals(branchId);
    }
}