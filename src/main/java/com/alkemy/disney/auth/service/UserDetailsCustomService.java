package com.alkemy.disney.auth.service;

import com.alkemy.disney.auth.dto.AuthRequest;
import com.alkemy.disney.auth.dto.AuthResponse;

import com.alkemy.disney.auth.dto.UserDto;
import com.alkemy.disney.auth.entity.EntityUser;

import com.alkemy.disney.auth.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import java.util.Collections;

@Service
public class UserDetailsCustomService implements  UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EntityUser userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    public boolean save(UserDto userDto) {
        EntityUser user = userRepository.findByUsername(userDto.getUsername());
        if(user != null) {
            throw new BadCredentialsException("Username is already in use");
        }
        EntityUser userEntity = new EntityUser();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity = userRepository.save(userEntity);

        return true;
    }

    public AuthResponse authenticate(AuthRequest request) throws Exception {

        UserDetails userDetails;

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final String jwt = jwtUtils.generateToken(userDetails);
        return new AuthResponse(jwt);
    }
}
