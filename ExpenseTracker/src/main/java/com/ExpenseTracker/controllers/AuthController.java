package com.ExpenseTracker.controllers;

import com.ExpenseTracker.config.CustomUserDetailsService;
import com.ExpenseTracker.config.JwtUtil;
import com.ExpenseTracker.dtos.LoginRequestDTO;
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
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO dto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(dto.getUsername());
        System.out.println(dto.getUsername());
        String token = jwtUtil.generateToken(dto.getUsername());


        String username = jwtUtil.getUsername(token);
        System.out.println(username+" "+dto.getUsername());
        return ResponseEntity.ok(token +" "+username);
    }
}
