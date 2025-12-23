package com.example.demo.security;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class JwtTokenProvider {

    public String createToken(Long userId, String email, Set<String> roles) {
        return "dummy-jwt-token";
    }

    public boolean validateToken(String token) {
        return token != null && !token.isBlank();
    }

    public Map<String, Object> getClaims(String token) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("token", token);
        claims.put("valid", validateToken(token));
        return claims;
    }
}
