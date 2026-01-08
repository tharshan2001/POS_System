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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String role = user.getRole().getName();

        // ✅ Full access roles
        if (role.equals("Super Admin") || role.equals("Admin")) {
            return true;
        }

        // ❌ No branch assigned
        if (user.getBranch() == null) {
            return false;
        }

        // ✅ Only own branch access
        return user.getBranch().getId().equals(branchId);
    }
}