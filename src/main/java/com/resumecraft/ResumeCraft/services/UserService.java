package com.resumecraft.ResumeCraft.services;

import com.resumecraft.ResumeCraft.config.JwtProvider;
import com.resumecraft.ResumeCraft.dto.UpdateProfileRequest;
import com.resumecraft.ResumeCraft.exception.UserException;
import com.resumecraft.ResumeCraft.model.User;
import com.resumecraft.ResumeCraft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email=jwtProvider.getEmailFromToken(jwt);

        User user=userRepository.findByEmail(email);

        if(user==null){
            throw new UserException("user not found with email"+email);
        }
        return user;
    }

    public User updateUserProfile(String jwt, UpdateProfileRequest updateProfileRequest) {
        // Step 1: Extract user ID from JWT
        String userId = jwtProvider.getIdFromToken(jwt);

        System.out.println("user id is...."+userId);

        // Step 2: Find user by ID
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Step 3: Update user details
        user.setFirstName(updateProfileRequest.getFirstName());
        user.setLastName(updateProfileRequest.getLastName());
        user.setEmail(updateProfileRequest.getEmail());

        // Encode the password if it was changed
        if (updateProfileRequest.getPassword() != null && !updateProfileRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateProfileRequest.getPassword()));
        }
        user.setRole("USER");
        // Step 4: Save the updated user
        return userRepository.save(user);
    }


    }
