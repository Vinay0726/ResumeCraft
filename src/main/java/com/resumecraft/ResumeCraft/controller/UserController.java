package com.resumecraft.ResumeCraft.controller;

import com.resumecraft.ResumeCraft.dto.UpdateProfileRequest;
import com.resumecraft.ResumeCraft.dto.UserAndResumeUpdateRequest;
import com.resumecraft.ResumeCraft.exception.UserException;
import com.resumecraft.ResumeCraft.model.Resume;
import com.resumecraft.ResumeCraft.model.User;
import com.resumecraft.ResumeCraft.services.ResumeService;
import com.resumecraft.ResumeCraft.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
 @Autowired
 private UserService userService;
    @Autowired
    private ResumeService resumeService;


    // Endpoint to find user profile by JWT token
    @GetMapping("/profile")
    public ResponseEntity<User> findUserProfileByJwt(@RequestHeader("Authorization") String jwt)throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserAndResume(@RequestHeader("Authorization") String jwt,
                                                 @RequestBody UserAndResumeUpdateRequest updateRequest) {
        try {
            // Step 1: Update user details using the service
            User updatedUser = userService.updateUserProfile(jwt, updateRequest.getUserProfile());

            // Step 2: Create or update resumes associated with the user
            List<Resume> updatedResumes = resumeService.updateUserResumes(updatedUser, updateRequest.getResumes());

            return ResponseEntity.ok("User and resumes updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating user and resumes: " + e.getMessage());
        }
    }




    @PostMapping("/resume")
    public ResponseEntity<?> createResume(@RequestBody Resume resume, @RequestHeader("Authorization") String jwt) {
        try {
            // Extract the user profile using the JWT
            User user = userService.findUserProfileByJwt(jwt);

            // Call the service method to create the resume and associate it with the user
            Resume createdResume = resumeService.createResume(resume, user);

            return new ResponseEntity<>(createdResume, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating resume: " + e.getMessage());
        }
    }



}

