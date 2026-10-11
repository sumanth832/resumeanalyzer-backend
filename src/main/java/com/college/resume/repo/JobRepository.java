package com.college.resume.repo;
import com.college.resume.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface JobRepository extends JpaRepository<Job,Long> { List<Job> findByActiveTrueOrderByCreatedAtDesc(); }
