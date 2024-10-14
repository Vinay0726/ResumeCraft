package com.resumecraft.ResumeCraft.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeUpdateRequest {
    private Long id; // Resume ID to identify which resume to update
    private String templateName;
    private String personalInfo;
    private String educationDetails;
    private String workExperience;
    private List<String> skills; // List of skills
    private String mobileNumber;
    private String email;  // Email ID
    private String githubLink;  // GitHub link
    private String linkedinLink;  // LinkedIn link
    private String twitterLink;  // Twitter link
}
