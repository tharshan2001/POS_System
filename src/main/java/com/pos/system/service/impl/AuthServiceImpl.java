package com.pos.system.service.impl;

import com.pos.system.dto.user.LoginRequest;
import com.pos.system.dto.user.LoginResponseDTO;
import com.pos.system.dto.user.Msg;
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


    //login
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
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);

        // 5. Return user info
        return new LoginResponseDTO(user.getFullName(), user.getRole().getName());
    }


    @Override
    public Msg logout(String token, HttpServletResponse response) {

        // Validate token first
        if (token == null || !jwtUtil.validateToken(token)) {
            throw new RuntimeException("Invalid or missing token");
        }

        // Clear the cookie
        Cookie cookie = new Cookie("jwt", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        new Msg("Logout SuccessFully !!");

        return null;
    }


}