package com.college.resume.repo;
import com.college.resume.model.ResumeAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ResumeAnalysisRepository extends JpaRepository<ResumeAnalysis,Long> {
 List<ResumeAnalysis> findByUser_IdOrderByCreatedAtDesc(Long userId);
 List<ResumeAnalysis> findAllByOrderByCreatedAtDesc();
 long countByUser_Id(Long userId);
}
