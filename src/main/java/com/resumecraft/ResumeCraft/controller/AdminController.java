package com.resumecraft.ResumeCraft.controller;

import com.resumecraft.ResumeCraft.model.User;
import com.resumecraft.ResumeCraft.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        // Check if the user exists in the repository
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            // If user exists, delete the user
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            // If user not found, return a 404 response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}
