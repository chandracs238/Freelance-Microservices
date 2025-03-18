package com.pcs.user_service.controller;

import com.pcs.user_service.service.CustomUserDetailsService;
import com.pcs.user_service.service.dto.AuthRequest;
import com.pcs.user_service.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> sayWelcome(){
        return ResponseEntity.ok("Welcome");
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        userDetailsService.saveUser(request.getUsername(), request.getPassword(), "FREELANCER");
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/api/auth/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return ResponseEntity.ok(jwtUtil.generateToken(request.getUsername()));
    }

}
