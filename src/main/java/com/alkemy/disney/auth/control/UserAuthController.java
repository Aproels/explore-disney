package com.alkemy.disney.auth.control;

import com.alkemy.disney.auth.dto.AuthRequest;
import com.alkemy.disney.auth.dto.AuthResponse;

import com.alkemy.disney.auth.dto.UserDto;
import com.alkemy.disney.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
    private UserDetailsCustomService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> signUp(@Valid @RequestBody UserDto user) throws Exception {
        service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> signIn(@Valid @RequestBody AuthRequest request) throws Exception {
        AuthResponse response = service.authenticate(request);
        return ResponseEntity.ok(response);
    }

}


