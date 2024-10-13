package com.resumecraft.ResumeCraft.repository;
import com.resumecraft.ResumeCraft.model.Resume;
import com.resumecraft.ResumeCraft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByUser(User user);
}
