package com.pos.system.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ValidateAdmin {

    public boolean hasAdminRole() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(auth ->
                        auth.getAuthority().equals("ADMIN")
                                || auth.getAuthority().equals("SUPER_ADMIN")
                );
    }

}
