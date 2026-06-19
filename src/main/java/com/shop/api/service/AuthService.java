package com.shop.api.service;

import com.shop.api.dto.AuthResponse;
import com.shop.api.dto.LoginRequest;
import com.shop.api.dto.RegisterRequest;

public interface AuthService {

    AuthResponse registrar(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
