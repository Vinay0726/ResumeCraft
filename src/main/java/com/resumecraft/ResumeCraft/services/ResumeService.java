package com.resumecraft.ResumeCraft.services;

import com.resumecraft.ResumeCraft.dto.ResumeUpdateRequest;
import com.resumecraft.ResumeCraft.exception.UserException;
import com.resumecraft.ResumeCraft.model.Resume;
import com.resumecraft.ResumeCraft.model.User;
import com.resumecraft.ResumeCraft.repository.ResumeRepository;
import com.resumecraft.ResumeCraft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResumeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResumeRepository resumeRepository;
    public Resume createResume(Resume resume, User user) throws UserException {
        // Create a new Resume object and associate the user
        resume.setUser(user);  // Associate the resume with the user

        // Save the resume fields
        resume.setTemplateName(resume.getTemplateName());
        resume.setPersonalInfo(resume.getPersonalInfo());
        resume.setEducationDetails(resume.getEducationDetails());
        resume.setWorkExperience(resume.getWorkExperience());
        resume.setSkills(resume.getSkills());  // Save the list of skills
        resume.setMobileNumber(resume.getMobileNumber());
        resume.setEmail(resume.getEmail());
        resume.setGithubLink(resume.getGithubLink());
        resume.setLinkedinLink(resume.getLinkedinLink());
        resume.setTwitterLink(resume.getTwitterLink());

        // Save the resume to the repository
        return resumeRepository.save(resume);
    }

    public List<Resume> updateUserResumes(User user, List<ResumeUpdateRequest> resumeUpdateRequests) throws UserException {
        List<Resume> updatedResumes = new ArrayList<>();

        for (ResumeUpdateRequest resumeRequest : resumeUpdateRequests) {
            Resume resume = new Resume();

            // Associate the resume with the user
            resume.setUser(user);

            // Update resume details
            resume.setTemplateName(resumeRequest.getTemplateName());
            resume.setPersonalInfo(resumeRequest.getPersonalInfo());
            resume.setEducationDetails(resumeRequest.getEducationDetails());
            resume.setWorkExperience(resumeRequest.getWorkExperience());
            resume.setSkills(resumeRequest.getSkills());
            resume.setMobileNumber(resumeRequest.getMobileNumber());
            resume.setEmail(resumeRequest.getEmail());
            resume.setGithubLink(resumeRequest.getGithubLink());
            resume.setLinkedinLink(resumeRequest.getLinkedinLink());
            resume.setTwitterLink(resumeRequest.getTwitterLink());

            // Save updated resume
            updatedResumes.add(resumeRepository.save(resume));
        }

        return updatedResumes;
    }

    public Optional<Resume> findResumeById(Long id, User user) {
        // Fetch resume based on ID and ensure it belongs to the user
        return resumeRepository.findByIdAndUserId(id, user.getId());
    }

}
