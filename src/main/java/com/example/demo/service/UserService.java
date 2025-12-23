package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.*;

public interface UserService {
    User register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
    User getByEmail(String email);
}
