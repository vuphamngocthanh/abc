package com.shop.webbe.controller;


import com.shop.webbe.dto.UserRequestDTO;
import com.shop.webbe.payload.request.AuthRequest;
import com.shop.webbe.payload.response.AuthResponse;
import com.shop.webbe.security.JwtTokenUtil;
import com.shop.webbe.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthApi {
    @Autowired AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            UserRequestDTO userRequestDTO  = userService.getPrincipalUser(request);
            String accessToken = jwtUtil.generateAccessToken(userRequestDTO);
            AuthResponse response = new AuthResponse(userRequestDTO.getUsername(), accessToken);
            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("no login");
        }
    }
}
