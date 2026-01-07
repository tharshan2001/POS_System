package com.pos.system.controller;

import com.pos.system.dto.user.LoginRequest;
import com.pos.system.dto.user.LoginResponseDTO;
import com.pos.system.dto.user.Msg;
import com.pos.system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequest request, HttpServletResponse response) {
        return authService.login(request, response);
    }


    @PostMapping("/logout")
    public Msg logout(@CookieValue(value = "jwt", required = false) String token, HttpServletResponse response) {

        return authService.logout(token, response);
    }


}