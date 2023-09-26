package com.customer.controller;

import com.customer.entity.AuthRequest;
import com.customer.entity.UserEntity;
import com.customer.repository.UserRepo;
import com.customer.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserRepo repo;
    @Autowired
    BCryptPasswordEncoder encoder;
    @PostMapping("/registers")
    public ResponseEntity<?> createUser(@RequestBody UserEntity user) {

        user.setPassword(encoder.encode(user.getPassword()));
        if (!repo.findByEmail(user.getEmail()).isPresent()) {
            UserEntity saved = repo.save(user);
            return ResponseEntity.ok(saved);
        }

        return ResponseEntity.ok().body("already exist!");
    }


    @PostMapping("/sign")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

        Authentication authentication
                = authenticationManager
                .authenticate(new
                        UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request");
        }
    }
}
