package com.pos.system.service;

import com.pos.system.controller.AuthController.LoginRequest;


import com.pos.system.dto.user.LoginResponseDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    LoginResponseDTO login(LoginRequest request, HttpServletResponse response);
}