package com.epam.esm.util.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean validateToken(String jwt, UserDetails userDetails);
}
