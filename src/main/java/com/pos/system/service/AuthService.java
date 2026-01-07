package com.pos.system.service;


import com.pos.system.dto.user.LoginRequest;
import com.pos.system.dto.user.LoginResponseDTO;
import com.pos.system.dto.user.Msg;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    LoginResponseDTO login(LoginRequest request, HttpServletResponse response);
    Msg logout(String token, HttpServletResponse response);

}