package com.auth.identity.service;

import com.auth.identity.payload.request.LoginRequest;
import com.auth.identity.payload.request.SignupRequest;
import com.auth.identity.payload.response.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
     JwtResponse authenticateUser(LoginRequest loginRequest);

     ResponseEntity<?>  registerUser(SignupRequest signupRequest);

}
