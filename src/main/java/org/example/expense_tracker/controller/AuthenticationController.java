package org.example.expense_tracker.controller;

import org.example.expense_tracker.dto.AuthRequestDTO;
import org.example.expense_tracker.dto.AuthResponseDTO;
import org.example.expense_tracker.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO dto){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword())
            );

            // Load user details
            UserDetails user = userDetailsService.loadUserByUsername(dto.getUsername());

            String token = jwtService.generateToken(user);
            return ResponseEntity.ok(new AuthResponseDTO(token));
        } catch (AuthenticationException e){
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
