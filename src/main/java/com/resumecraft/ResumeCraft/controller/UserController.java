package com.resumecraft.ResumeCraft.controller;

import com.resumecraft.ResumeCraft.config.JwtProvider;
import com.resumecraft.ResumeCraft.dto.LoginRequest;
import com.resumecraft.ResumeCraft.dto.response.AuthResponse;
import com.resumecraft.ResumeCraft.exception.UserException;
import com.resumecraft.ResumeCraft.model.User;
import com.resumecraft.ResumeCraft.repository.UserRepository;
import com.resumecraft.ResumeCraft.services.CustomUserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
 @Autowired
 private UserRepository userRepository;
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

}

