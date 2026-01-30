package com.ExpenseTracker.controllers;

import com.ExpenseTracker.config.CustomUserDetailsService;
import com.ExpenseTracker.config.JwtUtil;
import com.ExpenseTracker.dtos.LoginRequestDTO;
import com.ExpenseTracker.dtos.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager,JwtUtil jwtUtil,CustomUserDetailsService customUserDetailsService){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));


        String token = jwtUtil.generateToken(dto.getUsername());


        LoginResponseDTO responseDTO = new LoginResponseDTO(token, "Bearer");
        return ResponseEntity.ok(responseDTO);
    }
}
