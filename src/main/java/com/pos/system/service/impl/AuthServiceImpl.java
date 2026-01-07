package com.pos.system.service.impl;

import com.pos.system.controller.AuthController.LoginRequest;
import com.pos.system.dto.user.LoginResponseDTO;
import com.pos.system.entity.people.User;
import com.pos.system.repository.people.UserRepository;
import com.pos.system.security.JwtUtil;
import com.pos.system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponseDTO login(LoginRequest request, HttpServletResponse response) {
        // 1. Find the user by username
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Validate password
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        // 3. Generate JWT token
        String token = jwtUtil.generateToken(user.getUsername());

        // 4. Set token in HttpOnly cookie
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/"); // cookie is valid for the whole domain
        cookie.setMaxAge(24 * 60 * 60); // 1 day expiry
        response.addCookie(cookie);

        // 5. Return user info (without token)
        return new LoginResponseDTO(user.getFullName(), user.getRole().getName());
    }
}